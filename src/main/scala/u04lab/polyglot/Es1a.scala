package u04lab.polyglot

import u04lab.code.SecondDegreePolynomial

object Es1a extends App:

  object SecondDegreePolynomial:

    def apply(firstDegree: Double, secondDegree: Double, constant: Double): SecondDegreePolynomial =
      new SecondDegreePolynomialImpl(firstDegree, secondDegree, constant)

    def unapply(polynomial: SecondDegreePolynomial): scala.Option[(Double, Double, Double)] =
      scala.Some((polynomial.firstDegree, polynomial.secondDegree, polynomial.constant))

    private class SecondDegreePolynomialImpl(override val firstDegree: Double, override val secondDegree: Double, override val constant: Double) extends SecondDegreePolynomial:
      override def +(polynomial: SecondDegreePolynomial): SecondDegreePolynomial = polynomial match
        case SecondDegreePolynomial(s, f, c) => SecondDegreePolynomial(s + secondDegree, f + firstDegree, c + constant)

      override def -(polynomial: SecondDegreePolynomial): SecondDegreePolynomial = polynomial match
        case SecondDegreePolynomial(s, f, c) => SecondDegreePolynomial(s - secondDegree, f - firstDegree, c - constant)


  private val secondDegreePolynomial: SecondDegreePolynomial = SecondDegreePolynomial(10, 20, 0)

  println(s"Equals ${SecondDegreePolynomial(10, 20, 0) == secondDegreePolynomial }")
  println(s"Equals ${secondDegreePolynomial.toString}")