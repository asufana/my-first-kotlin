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

エンティティ上のフィールドは、Stringなどのプリミティブ型をラッピングした業務特化型のValueObjectとする

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

業務特化型として、保持する値に関連した振る舞いは、できるだけValueObjectに持たせるようにする

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

        fun findAll(): PostCollection {
            val list = repo().findAll()
            return list.toCollection()
        }
    }
```

自前リポジトリを用意することで任意の型で返却できるようにする

- JpaRepository インターフェースを利用したリポジトリ実装の場合、ジェネリクスで指定したエンティティかエンティティのリストでしか返却できない
- エンティティのリストも（VOと同様に）ドメイン特化型で返却したいため、自前のリポジトリを用意し、任意の型返却をできるようにする
- ただし基底処理として JpaRepository の機能を使いたいので、ラッピングする形で実装
- JpaRepository のインスタンスは、resolve ユーティリティを用いて DI コンテナから取得する






## TODO

#### ValueObject

- 空のコンストラクタをどうにかしたい
- バリデーション処理を用意する
- ValueObjectGroupの実装例を用意する





