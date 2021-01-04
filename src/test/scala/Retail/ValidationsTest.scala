package Retail

import org.junit._

@Test
class ValidationsTest {

  //<editor-fold desc="NullOrEmpty Test">

  @Test
  def testisNullOrEmptyTest1(): Unit ={
    assert(Validations.isNullOrEmpty(""))
  }
  @Test
  def testisNullOrEmptyTest2(): Unit ={
    assert(!Validations.isNullOrEmpty("Name"))
  }

  @Test
  def testisNullOrEmptyTest3(): Unit ={
    assert(Validations.isNullOrEmpty(null))
  }
//</editor-fold>

  //<editor-fold desc="Valid Contact Test">

  @Test
  def testValidContact1():Unit ={
    assert(Validations.validContactNumberRegex("(011) 456 7899"))
  }
  @Test
  def testValidContact2():Unit ={
    assert(Validations.validContactNumberRegex("(041).456.7899"))
  }

  @Test
  def testValidContact3():Unit ={
    assert(Validations.validContactNumberRegex("(043)-456-7899"))
  }

  @Test
  def testValidContact4():Unit ={
    assert(Validations.validContactNumberRegex("078-456-7899"))
  }

  @Test
  def testValidContact5():Unit ={
    assert(Validations.validContactNumberRegex("082 456 7899"))
  }

  @Test
  def testValidContact6():Unit ={
    assert(Validations.validContactNumberRegex("0734567899"))
  }
  //</editor-fold>

  //<editor-fold desc="Valid Email Test">

  @Test
  def testValidEmail1():Unit ={
    assert(Validations.validEmailAddressRegex("xxxx@gmal.com"))
  }

  @Test
  def testValidEmail2():Unit ={
    assert(Validations.validEmailAddressRegex("xxxx---@gmal.com"))
  }

  @Test
  def testValidEmail3():Unit ={
    assert(Validations.validEmailAddressRegex("xxxx@gmal.co"))
  }

  @Test
  def testValidEmail4():Unit ={
    assert(!Validations.validEmailAddressRegex(".xxxx@gmal.co.za"))
  }
  @Test
  def testValidEmail5():Unit ={
    assert(Validations.validEmailAddressRegex("xxxx123@gmal.co.za"))
  }
  @Test
  def tesValidEmail6():Unit ={
    assert(Validations.validEmailAddressRegex("xxxx123@gmal.com"))
  }

  //</editor-fold>

  //<editor-fold desc="Valid South African ID Test">

  @Test
  def testValidIDNumber1():Unit ={
    assert(Validations.validIDNumber("3601014800082"))
  }

  @Test
  def testValidIDNumber2():Unit ={
    assert(Validations.validIDNumber("9404075800081"))
  }

  @Test
  def testValidIDNumber4():Unit ={
    assert(Validations.validIDNumber("8204074800087"))
  }

  @Test
  def testValidIDNumber5():Unit ={
    assert(Validations.validIDNumber("8804074800082"))
  }

  //</editor-fold>

}
