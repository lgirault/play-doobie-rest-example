import com.google.inject.{TypeLiteral, AbstractModule}
import doobie.imports._

class Module extends AbstractModule {

  val xa : Transactor[IOLite] = DriverManagerTransactor[IOLite](
      "org.postgresql.Driver", "jdbc:postgresql:testdb", "testuser", ""
  )


  def configure() {
    bind(new TypeLiteral[Transactor[IOLite]]() {}).toInstance(xa) 
  }
}
