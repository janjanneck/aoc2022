
@main
def main(): Unit = {
  val source = scala.io.Source.fromFile("/Users/janrobert/dev/aoc2022/aoc2022/src/main/scala/day03/input.txt")
  val lines = try source.mkString.split("\n") finally source.close()
  val split = lines
    .map(line => line.splitAt(line.length / 2))
    .map((head, tail) => (head.toCharArray.toSet, tail.toCharArray.toSet))
    .map((head, tail) => head.intersect(tail))
    .map(_.head)
    .map(char => {
      if (char.isUpper) {
        char.toInt - 38
      } else {
        char.toInt - 96
      }
    })
    .toList
    .sum
  println(split)
}