package Retail

import org.junit.Test

@Test
class ArgumentsTest {

  //<editor-fold desc="Test Argument">

  @Test
  def testisArgValid1(): Unit = {
    assert(!Arguments.isArgValid(new Array[String](3)))
  }

  @Test
  def testisArgValid2(): Unit = {
    assert(Arguments.isArgValid(new Array[String](7)))
  }

  @Test
  def testisArgValid3(): Unit = {
    assert(Arguments.isArgValid(new Array[String](11)))
  }
  //</editor-fold>

}