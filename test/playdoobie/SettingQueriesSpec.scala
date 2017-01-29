package playdoobie

import doobie.imports._
import org.scalatest._
import doobie.scalatest.QueryChecker
import SettingRepository._



class SettingQueriesSpec extends FunSuite with Matchers with SettingQueryChecker {


  test("selectAllQuery"){ check(selectAllQuery) }

  test("getSettingQuery") { check(getSettingQuery("key") )}

  test("startWithQuery") { check(startWithQuery("key") ) }

  val s = Setting("k", "v")

  test("insertQuery") { check(insertQuery(s) ) }

  test("insertQueryReturning") { check(insertQueryReturning(s) ) }

  test("updateQuery") { check(updateQuery(s) ) }

  test("updateQueryReturning") { check(updateQueryReturning(s) ) }


}
