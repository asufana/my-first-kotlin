# My First Kotlin [![CircleCI](https://circleci.com/gh/asufana/my-first-kotlin/tree/master.svg?style=svg)](https://circleci.com/gh/asufana/my-first-kotlin/tree/master)

軽量DDDのプラクティスをベースに、Kotlin + Spring boot でのWebアプリケーション実装を検証する



## ValueObject

#### モデル構成の最小粒度をValueObjectとする

```kotlin
import javax.persistence.Column
import javax.persistence.Embeddable

//投稿名
@Embeddable
data class PostName(
        @Column(name = "name", nullable = false)
        val value: String = ""
)
```

エンティティ上のフィールドは、Stringなどのプリミティブ型をラッピングしたドメイン特化型のValueObjectとする

- データクラスで実装（ミュータブル・値で同値判断）
- １ValueObjectに付き、１つの値を保持する
- DBスキーマとして @Embeddable、@Column を指定
- ORMで利用しているJPA(Hibernate)が空コンストラクタを求めるため、空のデフォルト値を用意している
  - イケてないのでどうにかしたい



#### ValueObjectに振る舞いを持たせる

```kotlin
import com.github.asufana.domain.base.util.resolve
import com.github.asufana.domain.model.post.Post
import com.github.asufana.domain.model.post.repo.PostRepo

//投稿ID
data class PostId(val value: Long) {
    /** 投稿エンティティの取得 */
    fun toEntity(): Post {
        return resolve(PostRepo::class.java).findOne(this)
    }
}
```

ドメイン特化型として、保持する値に関連した振る舞いは、できるだけValueObjectに持たせるようにする

- 投稿IDは投稿エンティティを一意に指し示すため、投稿IDから投稿エンティティを取得する振る舞いを持たせることができる
- `val post = PostId(1L).toEntity()` のように利用できる




## Repository

#### 永続化に関する処理をリポジトリに集約する

```kotlin
import com.github.asufana.domain.base.util.resolve
import com.github.asufana.domain.model.post.Post
import com.github.asufana.domain.model.post.collection.PostCollection
import com.github.asufana.domain.model.post.collection.toCollection
import com.github.asufana.domain.model.post.vo.PostId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

//リポジトリ基底クラス
@Repository
private interface PostRepoBase : JpaRepository<Post, Long>

//投稿リポジトリ
class PostRepo {
  
    companion object {
      	//DIコンテナから基底クラスのインスタンスを取得
        private fun repo(): PostRepoBase = resolve(PostRepoBase::class.java)

        fun count(): Long {
            return repo().count()
        }

        fun findAll(): PostCollection {
            val list = repo().findAll()
            return list.toCollection()
        }

        fun save(post: Post): Post {
            return repo().saveAndFlush(post)
        }
    }
}
```

データベースアクセスはすべてリポジトリに移譲する

- リポジトリ自体に状態を持たせる必要がないので、companion object としてスタティック公開する



#### JpaRepositoryをラッピングしたリポジトリを用意する

```kotlin
    companion object {
      	//DIコンテナから基底クラスのインスタンスを取得
        private fun repo(): PostRepoBase = resolve(PostRepoBase::class.java)

      	//投稿一覧をドメイン特化型の投稿コレクションクラスにラッピングして返却する
        fun findAll(): PostCollection {
            val list = repo().findAll()
            return list.toCollection()
        }
    }
```

自前リポジトリを用意することで任意の型で返却できるようにする

- JpaRepository インターフェースを利用したリポジトリ実装の場合、ジェネリクスで指定したエンティティの型でしか返却できない
- エンティティのリストも（VOと同様に）ドメイン特化型で返却したいため、自前のリポジトリを用意し、任意の型返却をできるようにする
- ただし基底処理として JpaRepository の機能を使いたいので、ラッピングする形で実装
- JpaRepository のインスタンスは、resolve ユーティリティを用いて DI コンテナから取得する




## Collection

#### プリミティブなリストをラッピングし、ドメイン特化リストとする

```kotlin
package com.github.asufana.domain.model.post.collection

import com.github.asufana.domain.base.collection.AbstractCollection
import com.github.asufana.domain.model.post.Post
import com.github.asufana.domain.model.tag.collection.TagCollection
import com.github.asufana.domain.model.tag.collection.toCollection

/** Collection変換 */
fun List<Post>.toCollection(): PostCollection {
    return PostCollection(this)
}

class PostCollection(list: List<Post>) : AbstractCollection<PostCollection, Post>(list) {

    /** コメントある投稿のみ抽出 */
    fun hasComments(): PostCollection {
        return filter { !it.comments().isEmpty() }
    }

    /** タグ一覧に変換 */
    fun toTags(): TagCollection {
        val tags = list.flatMap { it.tags().list }
        return tags.toCollection()
    }
}
```

ValueObjectと同様、Kotlinプリミティブなリストクラスはドメインのファーストシチズンとして利用しない

- プリミティブなリストクラスを操作するということは、他のクラスがリストの情報を取得して何かするということであり、これではデータと振る舞いが生き別れてしまう
- データと振る舞いをカプセル化すること、業務に特化したメソッドのみを公開するために、リストクラスもラッピングした上で利用する

#### 閉じた操作で構成する

```kotlin
    @Test
    fun testHasCommentAndTag() {
        //コメントとタグを持つ投稿を登録する
        val comment = CommentTest.save01()
        val tag = TagTest.save01()
        comment.post.assign(tag).save()

        //投稿一覧の取得
        val posts = PostRepo.findAll()
      
        //投稿一覧の操作
        val tags = posts
      		.hasComments()
      		.hasTags()
      		.toTags()
      		.sort()
      
        //タグ一覧に変換され、要素数は１であること
        assertThat(tags is TagCollection).isTrue()
        assertThat(tags.count()).isEqualTo(1)
    }
```

- メソッド戻り値を常に自身のクラスとすることで、コレクション内で概念が閉じた構成とする
- こうすることで、コレクション上のどのようなメソッドを呼び出そうとコレクション内で処理が閉じているため、小さなメソッドを組み合わせて任意の処理を構成することが容易に、かつ安全に実現できる
- たとえば上の例では「コメントがあり、かつタグ付けされている投稿」を抽出しているが、 `posts.hasCommentsAndTags()` といった要件に対して専用のメソッドは用意する必要はなく、
- `posts.hasComments().hasTags()` という閉じた操作の組み合わせで実現している
- また `toTags()` メソッドでタグ一覧に変換することで、タグ一覧コレクションでも閉じた操作で任意の処理を組み合わせて処理することができる



## Entity

#### ValueObject, Repository, Collectionなどを取りまとめる

```kotlin
import com.github.asufana.domain.base.entity.AbstractEntity
import com.github.asufana.domain.model.comment.collection.CommentCollection
import com.github.asufana.domain.model.comment.repo.CommentRepo
import com.github.asufana.domain.model.post.repo.PostRepo
import com.github.asufana.domain.model.post.vo.PostId
import com.github.asufana.domain.model.post.vo.PostName
import com.github.asufana.domain.model.tag.Tag
import com.github.asufana.domain.model.tag.collection.TagCollection
import com.github.asufana.domain.model.tag.repo.TagAssignRepo
import org.hibernate.internal.util.StringHelper.*
import javax.persistence.Entity
import javax.persistence.Table

/** 投稿 */
@Entity
@Table(name = "posts")
class Post private constructor() : AbstractEntity() {

    lateinit var name: PostName

    constructor(name: PostName) : this() {
        this.name = name

        isSatisfied()
    }

  　//保存条件
    override fun isSatisfied() {
        assert(isNotEmpty(name.value))
    }

    fun id(): PostId {
        return PostId(this.id)
    }

    //関連付けられたコメント一覧
    fun comments(): CommentCollection {
        return CommentRepo.findBy(this)
    }

    //保存
    fun save(): Post {
        isSatisfied()
        return PostRepo.save(this)
    }
}
```

- フィールドはVOで構成
- コンストラクタは、セカンダリコンストラクタにドメイン要件に基づいた引数を整備する
  - プライマリコンストラクタはHibernate要件のため引数なしをプライベートで用意
- isSatistisfied() でコンストラクタとsave時に、当該エンティティの保存条件を満たすか確認する
- フィールドの保存条件はVO自体で実施する
- 保存や関連取得の処理はリポジトリに移譲する







## TODO

#### ValueObject

- 空のコンストラクタをどうにかしたい
- バリデーション処理を用意する
- ValueObjectGroupの実装例を用意する





