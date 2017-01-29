package playdoobie

import org.scalatest._
import SettingRepository._ 

class SettingRepositorySpec
  extends WordSpec
  with Matchers
  with OptionValues
  with SettingQueryChecker {

  val s1 = Setting("k1", "toto")
  val s2 = Setting("k2", "titi")

  override protected def beforeAll() : Unit = {
    super.beforeAll()
    perform(createSetting(s1))
    perform(createSetting(s2))
  }


  "SettingRepository" should {

    "retrieve existing settings" in {
       perform(getSetting("k1")).value shouldBe s1
    }

    "return none if setting does not exist" in {
      perform(getSetting("k3")) shouldBe empty
    }

  }






 perform(listSettings(None)) foreach println
}
