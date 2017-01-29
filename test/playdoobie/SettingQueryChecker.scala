package playdoobie

import doobie.imports._
import doobie.scalatest.QueryChecker
import org.scalatest.{BeforeAndAfterAll, Suite}

/**
  * Created by lorilan on 1/29/17.
  */

trait SettingQueryChecker extends QueryChecker with BeforeAndAfterAll {
  self: Suite =>

  override def transactor: Transactor[IOLite] =
    DriverManagerTransactor[IOLite](
      "org.postgresql.Driver", "jdbc:postgresql:testdb", "testuser", ""
    )

  val drop: Update0 =
    sql"""DROP TABLE IF EXISTS testtable""".update

  val create: Update0 =
    sql"""CREATE TABLE testtable (
                        key VARCHAR(256) PRIMARY KEY,
                        value VARCHAR(256) NOT NULL
                        )
      """.update



  def perform[A](c : ConnectionIO[A]) : A =
    c.transact(transactor).unsafePerformIO

  override protected def beforeAll() : Unit = {
    perform(drop.run)
    perform(create.run)
  }

}
