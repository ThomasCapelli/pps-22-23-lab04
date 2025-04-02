package u04lab.polyglot

import u04lab.code.SecondDegreePolynomial
object Es1b extends App:

  object SecondDegreePolynomial:

    def apply(secondDegree: Double, firstDegree: Double, constant: Double): SecondDegreePolynomial =
      SecondDegreePolynomialImpl(secondDegree, firstDegree, constant)

    def unapply(polynomial: SecondDegreePolynomial): scala.Option[(Double, Double, Double)] =
      scala.Some((polynomial.secondDegree, polynomial.firstDegree, polynomial.constant))

    private case class SecondDegreePolynomialImpl(secondDegree: Double,
                                                  firstDegree: Double,
                                                  constant: Double) extends SecondDegreePolynomial:
      override def +(polynomial: SecondDegreePolynomial): SecondDegreePolynomial = polynomial match
        case SecondDegreePolynomial(s, f, c) => SecondDegreePolynomial(s + secondDegree, f + firstDegree, c + constant)

      override def -(polynomial: SecondDegreePolynomial): SecondDegreePolynomial = polynomial match
        case SecondDegreePolynomial(s, f, c) => SecondDegreePolynomial(s - secondDegree, f - firstDegree, c - constant)

  private val secondDegreePolynomial: SecondDegreePolynomial = SecondDegreePolynomial(10, 20, 0)

  println(s"Equals ${SecondDegreePolynomial(10, 20, 0) == secondDegreePolynomial}")
  println(s"Equals ${secondDegreePolynomial.toString}")

