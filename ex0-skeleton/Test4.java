class Test4 {

  public static void main(String[] args) {

    CS2030STest we = new CS2030STest();

    we.expect("Ex0 with 10 points and seed 4",
        Ex0.estimatePi(10, 4),
        2.4);

    we.expect("Ex0 with 100 points and seed 4",
        Ex0.estimatePi(100, 4),
        3.16);

    we.expect("Ex0 with 1000 points and seed 4",
        Ex0.estimatePi(1000, 4),
        3.148);

    we.expect("Ex0 with 10000 points and seed 4",
        Ex0.estimatePi(10000, 4),
        3.1756);

    we.expect("Ex0 with 100000 points and seed 4",
        Ex0.estimatePi(100000, 4),
        3.1438);

    we.expect("Ex0 with 1000000 points and seed 4",
        Ex0.estimatePi(1000000, 4),
        3.140624);

    we.expect("Ex0 with 10000000 points and seed 4",
        Ex0.estimatePi(10000000, 4),
        3.1411096);

    we.expect("Ex0 with 100000000 points and seed 4",
        Ex0.estimatePi(100000000, 4),
        3.14166464);
  }
}

