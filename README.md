# My First Kotlin [![CircleCI](https://circleci.com/gh/asufana/my-first-kotlin/tree/master.svg?style=svg)](https://circleci.com/gh/asufana/my-first-kotlin/tree/master)

軽量DDDのプラクティスをベースに実装する





## ValueObject

##### モデル構成の最小粒度をValueObjectとする

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



##### ValueObjectに振る舞いを持たせる

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
-  `val post = PostId(1L).toEntity()` のように利用できる





## TODO

#### ValueObject

- 空のコンストラクタをどうにかしたい
- バリデーション処理を用意する



