import java.io.File;

public class Main {
  public static void main(String[] args) {

    CRUD<Carro> fileCar;
    // Carros de exemplo
    Carro c1 = new Carro(-1, "Gallardo", "Lamborghini", 2012);
    Carro c2 = new Carro(-1, "Hilux", "Toyota", 2020);
    Carro c3 = new Carro(-1, "Ka", "Ford", 2009);
    Carro c4 = new Carro(-1, "Civic", "Honda", 2023);
    Carro c5 = new Carro(-1, "Gol", "Wolksvagen", 2025);

    int id1, id2, id3, id4, id5; // ID dos registros
    boolean ok;

    try {
      // Abre (cria) o arquivo de livros
      new File("car.db").delete(); // Apaga o arquivo anterior, para criar um novo
      fileCar = new CRUD<>(Carro.class.getConstructor(), "car.db");

      System.out.println("REGISTROS CRIADOS COM SUCESSO!");

      id1 = fileCar.create(c1);
      id2 = fileCar.create(c2);
      id3 = fileCar.create(c3);
      id4 = fileCar.create(c4);
      id5 = fileCar.create(c5);

      System.out.println("\nPROCURANDO REGISTROS:");
      // Procura os registros e os printa na tela
      System.out.println(fileCar.read(id1));
      System.out.println(fileCar.read(id2));
      System.out.println(fileCar.read(id3));
      System.out.println(fileCar.read(id4));
      System.out.println(fileCar.read(id5));

      System.out.println("\nATUALIZANDO ALGUNS REGISTROS:");

      c1.brand = "Aston Martin"; // Atualiza com um atributo maior do que o anterior
      ok = fileCar.update(c1);
      if (ok)
        System.out.println("\nREGISTRO " + id1 + " ATUALIZADO COM SUCESSO!");
      System.out.println(fileCar.read(id1)); // Printa o arquivo atualizado

      c2.brand = "Fiat";
      ok = fileCar.update(c2); // Atualiza com um atributo menor do que o anterior
      if (ok)
        System.out.println("\nREGISTRO " + id2 + " ATUALIZADO COM SUCESSO!");
      System.out.println(fileCar.read(id2));

      c5.year = 1993;
      ok = fileCar.update(c5); // Atualiza com um atributo menor do que o anterior
      if (ok)
        System.out.println("\nREGISTRO " + id5 + " ATUALIZADO COM SUCESSO!");
      System.out.println(fileCar.read(id5));

      System.out.println("\nDELETANDO ALGUNS REGISTROS:");

      if (fileCar.delete(id3) == true)
        System.out.println("\nCARRO 3 EXCLUIDO COM SUCESSO!");
      else
        System.out.println(c3);

      if (fileCar.delete(0) == true)
        System.out.println("\nCARRO EXCLUIDO COM SUCESSO!");

      System.out.println("\nPROCURANDO REGISTROS:");
      System.out.println(fileCar.read(id1));
      System.out.println("\n" + fileCar.read(id2));
      System.out.println("\n" + fileCar.read(id3));
      System.out.println(fileCar.read(id4));
      System.out.println(fileCar.read(id5));

    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}