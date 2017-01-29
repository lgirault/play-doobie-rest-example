package playdoobie

import doobie.imports._
import fs2.interop.cats._

object SettingRepository {

  val selectQuery : Fragment = fr"SELECT * FROM testtable"

  val selectAllQuery : Query0[Setting] = selectQuery.query

  def getSettingQuery(key : String) : Query0[Setting] =
    (selectQuery ++ fr" AS t WHERE t.key = $key").query[Setting]

  def startWithQuery(startString : String) : Query0[Setting] =
    (selectQuery ++ fr" AS t WHERE lower(t.key) LIKE ${startString.toLowerCase + '%'}").query[Setting]

  def insertFr(s: Setting) : Fragment =
    fr"INSERT INTO testtable VALUES (${s.key}, ${s.value})"

  def insertQuery(s : Setting) : Update0 =
    insertFr(s).update

  def returningFr : Fragment = fr" RETURNING * "

  def insertQueryReturning(s : Setting) : Query0[Setting] =
    (insertFr(s) ++ returningFr).query

  def updateFr(s : Setting) : Fragment =
    fr"UPDATE testtable SET value = ${s.value} WHERE key = ${s.key}" 
  
  def updateQuery(s : Setting) : Update0 = updateFr(s).update

  def updateQueryReturning(s : Setting) : Query0[Setting] =
    (updateFr(s) ++ returningFr).query

  def listSettings(startStringOpt : Option[String]) : ConnectionIO[List[Setting]] = 
    (startStringOpt match {
      case Some(startString) => startWithQuery(startString)
      case None => selectAllQuery
    }).list

  def getSetting(key : String) : ConnectionIO[Option[Setting]] =
    getSettingQuery(key).option

  def updateSetting(s : Setting) : ConnectionIO[Setting] = 
    updateQueryReturning(s).unique

  def updateSetting0(s : Setting) : ConnectionIO[Int] =
    updateQuery(s).run

  def createSetting(s : Setting) : ConnectionIO[Setting] =
    insertQueryReturning(s).unique
  
  def createSetting0(s : Setting) : ConnectionIO[Int] =
    insertQuery(s).run


}
