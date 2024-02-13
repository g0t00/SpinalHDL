package spinal.core
import spinal.tester.SpinalAnyFunSuite
import spinal.core.sim._
import spinal.core._

class NameTests extends SpinalAnyFunSuite {
  test("test setName assertions") {
    val vectors = Seq(
      ("foo", true),
      ("FOO", true),
      ("bar123", true),
      ("foo bar", false),
      ("foo__bar", false),
      (
        "_asd",
        false
      ),
      (
        "1_asd",
        false
      )
    )
    for (vec <- vectors) {

      // SimConfig.withGhdl
      //   .compile(new Component {
      //     val testReg = RegInit(B("x0F"))
      //     if (vec._2) {
      //       testReg.setName(vec._1)
      //     } else {
      //       assertThrows[java.lang.IllegalArgumentException](testReg.setName(vec._1))
      //     }
      //     setDefinitionName("NameTests")
      //   })
      //   .doSim { dut =>
      //     dut.clockDomain.forkStimulus(10)
      //     dut.clockDomain.waitSampling(10)
      //   }
      SimConfig
        .compile(new Component {
          val testReg = RegInit(B("x0F"))
          if (vec._2) {
            testReg.setName(vec._1)
          } else {
            assertThrows[java.lang.IllegalArgumentException](testReg.setName(vec._1))
          }
          setDefinitionName("NameTests")
        })
        .doSim { dut =>
          dut.clockDomain.forkStimulus(10)
          dut.clockDomain.waitSampling(10)
        }

    }
  }

}
