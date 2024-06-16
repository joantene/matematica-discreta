import java.lang.AssertionError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/*
 * Aquesta entrega consisteix en implementar tots els mètodes annotats amb "// TODO". L'enunciat de
 * cada un d'ells és al comentari de la seva signatura i exemples del seu funcionament als mètodes
 * `Tema1.tests`, `Tema2.tests`, etc.
 *
 * L'avaluació consistirà en:
 *
 * - Si el codi no compila, la nota del grup serà un 0.
 *
 * - Si heu fet cap modificació que no sigui afegir un mètode, afegir proves als mètodes "tests()" o
 *   implementar els mètodes annotats amb "// TODO", la nota del grup serà un 0.
 *
 * - Principalment, la nota dependrà del correcte funcionament dels mètodes implemnetats (provant
 *   amb diferents entrades).
 *
 * - Tendrem en compte la neteja i organització del codi. Un estandard que podeu seguir és la guia
 *   d'estil de Google per Java: https://google.github.io/styleguide/javaguide.html . Algunes
 *   consideracions importants:
 *    + Entregau amb la mateixa codificació (UTF-8) i finals de línia (LF, no CR+LF)
 *    + Indentació i espaiat consistent
 *    + Bona nomenclatura de variables
 *    + Declarar les variables el més aprop possible al primer ús (és a dir, evitau blocs de
 *      declaracions).
 *    + Convé utilitzar el for-each (for (int x : ...)) enlloc del clàssic (for (int i = 0; ...))
 *      sempre que no necessiteu l'índex del recorregut.
 *
 * Per com està plantejada aquesta entrega, no necessitau (ni podeu) utilitzar cap `import`
 * addicional, ni qualificar classes que no estiguin ja importades. El que sí podeu fer és definir
 * tots els mètodes addicionals que volgueu (de manera ordenada i dins el tema que pertoqui).
 *
 * Podeu fer aquesta entrega en grups de com a màxim 3 persones, i necessitareu com a minim Java 10.
 * Per entregar, posau a continuació els vostres noms i entregau únicament aquest fitxer.
 * - Nom 1:Joan Tenerife Perelló
 * - Nom 2: Alberto Segura Calvo
 * 
 *
 * L'entrega es farà a través d'una tasca a l'Aula Digital que obrirem abans de la data que se us
 * hagui comunicat i vos recomanam que treballeu amb un fork d'aquest repositori per seguir més
 * fàcilment les actualitzacions amb enunciats nous. Si no podeu visualitzar bé algun enunciat,
 * assegurau-vos de que el vostre editor de texte estigui configurat amb codificació UTF-8.
 */
class Entrega {
  /*
   * Aquí teniu els exercicis del Tema 1 (Lògica).
   *
   * La majoria dels mètodes reben de paràmetre l'univers (representat com un array) i els predicats
   * adients (per exemple, `Predicate<Integer> p`). Per avaluar aquest predicat, si `x` és un
   * element de l'univers, podeu fer-ho com `p.test(x)`, que té com resultat un booleà (true si
   * `P(x)` és cert). Els predicats de dues variables són de tipus `BiPredicate<Integer, Integer>` i
   * similarment s'avaluen com `p.test(x, y)`.
   *
   * En cada un d'aquests exercicis (excepte el primer) us demanam que donat l'univers i els
   * predicats retorneu `true` o `false` segons si la proposició donada és certa (suposau que
   * l'univers és suficientment petit com per poder provar tots els casos que faci falta).
   */
  static class Tema1 {
    /*
     * Donat n > 1, en quants de casos (segons els valors de veritat de les proposicions p1,...,pn)
     * la proposició (...((p1 -> p2) -> p3) -> ...) -> pn és certa?
     *
     * Vegeu el mètode Tema1.tests() per exemples.
     */
     static int exercici1(int n) {
            //Declaració de variable contador
            int cont = 0; 
            //Declaració d'un array boolea per poder saber si el valor es 1 o es 0
            boolean [] proposicions= new boolean[n];
            
            //Inicialitzam tots els valors de l'indice a true 
            for (int indice=0;indice<proposicions.length;indice++) {
                proposicions[indice]=true;
            }
            do {
                //Provam els casos de les proposicions amb tots els casos posibles (00,01,10,11)
                 proposicions[0]=!proposicions[0];
            for (int indice=1;indice<proposicions.length;indice++) {
                if (!proposicions[indice-1]) {
                    proposicions[indice]=!proposicions[indice];
                } else {
                    break;
                }
            }
                //Comprovam si el cas funciana i l'afegim al contador
                boolean cas = proposicions[0];  
            for (int indice=1;indice<proposicions.length;indice++) {
                cas=(!cas)||(proposicions[indice]);
            }
            if (cas) {
                cont++;
            }
                //Verificació de si tots els casos són verdaders cosa que vol dir que podem sortir del bucle
            } while (!vertader(proposicions));

            //resultado
            return cont;
        }


        //metode vertader
        public static boolean vertader(boolean[] proposicions) {
            for (boolean boolea : proposicions) {
                if (!boolea) {
                    return false;
                }
            }
            return true;
        }


    /*
     * És cert que ∀x : P(x) -> ∃!y : Q(x,y) ?
     */
      static boolean exercici2(int[] universe, Predicate<Integer> p, BiPredicate<Integer, Integer> q) {
           //Declaracion de una variable de tipo boolean para devolver el valor
        boolean valor=true;
        // Bucle para comprovar todo x en el universo
        for(int x=0;x<universe.length;x++) {
            //Condicional para comprovar si se cumple P(x)
            if(p.test(universe[x])) {
                //Declaración de una variable de tipo int para que haga de contador
                int cont=0;
                //Bucle para comprovar toda y en el universo
                for(int y=0;y<universe.length;y++) {
                    //Condicional para comprovar si se cumple Q(x,y)
                    if (q.test(universe[x],universe[y])) {
                         //Si se cumple aumentamos en un caso el contador
                         cont++;
                    }
                }
              //Comprovamos si el numero del contador es diferente de 1{
            //Si lo es variable valor=false
            if(cont!=1) {
                valor=false;
            }
            }
        }
        //Devolvemos el valor
            return valor; // TODO
        }

    /*
     * És cert que ∃x : ∀y : Q(x, y) -> P(x) ?
     */
    static boolean exercici3(int[] universe, Predicate<Integer> p, BiPredicate<Integer, Integer> q) {
            //comprovam els casos de x
            for (int x:universe) {
                boolean predicat=true;
                //Cercam si hi ha algun cas per y el qual sigui negatiu cosa que vol dir que serà fals
                for (int y:universe) {
                    if (!q.test(x,y) && p.test(x)) {
                        predicat=false;
                    }
                }
                //si trobam algun cas de y retornam el valor vertader
                if (predicat=true) {
                    return predicat;
                }
            }
            return false; // TODO
        }

    /*
     * És cert que ∃x : ∃!y : ∀z : P(x,z) <-> Q(y,z) ?
     */
    static boolean exercici4(int[] universe, BiPredicate<Integer, Integer> p, BiPredicate<Integer, Integer> q) {
            int conty=0;
            //Miram si hi ha alguna x
            for (int x : universe) {
                conty=0;
                //Comprovam que només hi hagi una y
                for (int y : universe) {
                    boolean casZ = true;
                    //S ha de complir per tot z
                    for (int z : universe) {
                        if (p.test(x,z)!=q.test(y, z)){
                            casZ = false;
                        }
                    }
                    if (casZ) {
                        conty++;
                    }
                }
                //Condicional que s executa quan s'ha trobat un sol cas de y
                if (conty==1) {
                    return true;
                }
            }
            return false; // TODO
        }

    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1

      // p1 -> p2 és cert exactament a 3 files
      // p1 p2
      // 0  0  <-
      // 0  1  <-
      // 1  0
      // 1  1  <-
      assertThat(exercici1(2) == 3);

      // (p1 -> p2) -> p3 és cert exactament a 5 files
      // p1 p2 p3
      // 0  0  0
      // 0  0  1  <-
      // 0  1  0
      // 0  1  1  <-
      // 1  0  0  <-
      // 1  0  1  <-
      // 1  1  0
      // 1  1  1  <-
      assertThat(exercici1(3) == 5);

      // Exercici 2
      // ∀x : P(x) -> ∃!y : Q(x,y)
      assertThat(
          exercici2(
            new int[] { 1, 2, 3 },
            x -> x % 2 == 0,
            (x, y) -> x+y >= 5
          )
      );

      assertThat(
          !exercici2(
            new int[] { 1, 2, 3 },
            x -> x < 3,
            (x, y) -> x-y > 0
          )
      );

      // Exercici 3
      // És cert que ∃x : ∀y : Q(x, y) -> P(x) ?
      assertThat(
          exercici3(
            new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
            x -> x % 3 != 0,
            (x, y) -> y % x == 0
          )
      );

      assertThat(
          exercici3(
            new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
            x -> x % 4 != 0,
            (x, y) -> (x*y) % 4 != 0
          )
      );

      // Exercici 4
      // És cert que ∃x : ∃!y : ∀z : P(x,z) <-> Q(y,z) ?
      assertThat(
          exercici4(
            new int[] { 0, 1, 2, 3, 4, 5 },
            (x, z) -> x*z == 1,
            (y, z) -> y*z == 2
          )
      );

      assertThat(
          !exercici4(
            new int[] { 2, 3, 4, 5 },
            (x, z) -> x*z == 1,
            (y, z) -> y*z == 2
          )
      );
    }
  }

  /*
   * Aquí teniu els exercicis del Tema 2 (Conjunts).
   *
   * Per senzillesa tractarem els conjunts com arrays (sense elements repetits). Per tant, un
   * conjunt de conjunts d'enters tendrà tipus int[][].
   *
   * Les relacions també les representarem com arrays de dues dimensions, on la segona dimensió
   * només té dos elements. Per exemple
   *   int[][] rel = {{0,0}, {0,1}, {1,1}, {2,2}};
   * i també donarem el conjunt on està definida, per exemple
   *   int[] a = {0,1,2};
   * Als tests utilitzarem extensivament la funció generateRel definida al final (també la podeu
   * utilitzar si la necessitau).
   *
   * Les funcions f : A -> B (on A i B son subconjunts dels enters) les representam o bé amb el seu
   * graf o amb un objecte de tipus Function<Integer, Integer>. Sempre donarem el domini int[] a, el
   * codomini int[] b. En el cas de tenir un objecte de tipus Function<Integer, Integer>, per aplicar
   * f a x, és a dir, "f(x)" on x és d'A i el resultat f.apply(x) és de B, s'escriu f.apply(x).
   */
  static class Tema2 {
    /*
     * Calculau el nombre d'elements del conjunt (a u b) × (a \ c)
     *
     * Podeu soposar que `a`, `b` i `c` estan ordenats de menor a major.
     */
     static int exercici1(int[] a, int[] b, int[] c) {
      //Declaració de distints arrays per almacenar la unio i la diferencia
      int [] unio= new int [20];
      int [] dif=new int [100];
      //Declaracio d'una variable contador per utilitzalo d'indice
      int contUnio=0;
      //Comprovam els casos en els que son igual i les guardam
      for (int indicea=0;indicea<a.length;indicea++) {
        for (int indiceb=0;indiceb<b.length;indiceb++) {
          if (a[indicea]==b[indiceb]) {
            unio[contUnio]=a[indicea];
            contUnio++;
            
          }
        }
      }
      //Declaracio d'una variable contador per utilitzalo d'indice
      int contDif=0;
      
      //Comprovam els casos en els que s hagi de fer la diferencia i les guardam
       // Afegim els elements de 'a' que no estiguin a 'c' a la diferència
    for (int indicea = 0;indicea< a.length;indicea++) {
        boolean x=false;
        for (int indicec= 0;indicec<c.length;indicec++) {
            if (a[indicea]==c[indicec]) {
                x=true;
                break;
            }
        }
        if (!x) {
            dif[contDif++]=a[indicea];
            contDif++;
        }
    }
 
       int prodCar=contUnio*contDif;
      return prodCar; // TODO
    }

    /*
     * La clausura d'equivalència d'una relació és el resultat de fer-hi la clausura reflexiva, simètrica i
     * transitiva simultàniament, i, per tant, sempre és una relació d'equivalència.
     *
     * Trobau el cardinal d'aquesta clausura.
     *
     * Podeu soposar que `a` i `rel` estan ordenats de menor a major (`rel` lexicogràficament).
     */
      static int exercici2(int[] a, int[][] rel) {
            //numero de elementos
            HashSet<int[]> elements = new HashSet<>();
            for (int e : a) {
                int[] l={e, e};
                elements.add(l);
            }//Clausula reflexiva
            for (int[] e : rel) {
                 if (e[0]==e[1]) {
                    continue;
                }
                int[] l = {e[1], e[0]};
                  elements.add(e);
                elements.add(l);
            } //Clausula simètrica 
            for (int indice=0;indice<a.length-1;indice++) {
                for (int[] y : rel) {
                  for (int[] x : rel) {
                      if (x[0]==y[1]) {
                           elements.add(new int[]{y[0], x[1]});
                        }

                    }
                }
            }//Clausula transitiva 
            //Retornam el valor
            return elements.size(); // TODO
        }

    /*
     * Comprovau si la relació `rel` és un ordre total sobre `a`. Si ho és retornau el nombre
     * d'arestes del seu diagrama de Hasse. Sino, retornau -2.
     *
     * Podeu soposar que `a` i `rel` estan ordenats de menor a major (`rel` lexicogràficament).
     */
     static int exercici3(int[] a, int[][] rel) {
            //Declaracio de un boolea inicialitzada en falsa
               boolean bool=false;
              if(!reflexiva(a, rel) || !antisimetric(a, rel) || !transitiba(a, rel)) {bool=true;}
            if (bool) {
                return -2;
            }
            return aristes(rel);
        }
        static int aristes(int[][] rel) {
            HashSet<int[]> elelments=new HashSet<>();
              int cont=0;
               for (int[] indice : rel) {
                if (indice[0]!=indice[1]) {
                    elelments.add(new int[]{indice[0], indice[1]});
                }
            }
             int indice = 0;
               int[][] composicio=new int[elelments.size()][2];
             for (int[] indice1 : elelments) {
                  if (ExisteixArista(composicio,indice1)) {
                       composicio[indice++]=indice1;
                }
            }
            return indice;
        }
        static boolean ExisteixArista(int[][] arr, int[] comp) {
            for (int[] indice : arr) {
                boolean bool1=false;
                if (indice[0]==0&&(indice[1]==comp[0]||indice[1]==comp[1])&&
                        (indice[0]==comp[0]||indice[0]==comp[1])) {
                    bool1=true;
                }
                if (bool1) {
                    return true;
                }
                boolean bool2=false;
                if (indice[1]==0&&(indice[0]==comp[0]||indice[0]==comp[1])
                        &&(indice[1]==comp[0]||indice[1]==comp[1])) {
                bool2=true;
            }
                if (bool2) {
                    return true;
                }
            }
            return false;
        }
        static boolean reflexiva(int[] a, int[][] rel) {
            for (int indice : a) {
                boolean existeix;
                 existeix=false;
                  for (int[] comparacion : rel) {
                    if (indice==comparacion[0]&&indice==comparacion[1]) {
                         existeix=true;
                    }
                }
                    if (!existeix) {
                       return existeix;
                }
            }
            return true;
        }
        static boolean antisimetric(int[] a, int[][] rel) {
            for (int[] indice : rel) {
                for (int[] par2 : rel) {
                    if (indice[0]==par2[1]&&
                            indice[1]==par2[0]&&indice!= par2) {
                        return false;
                    }
                }
            }
            return true;
        }
        static boolean transitiba(int[] a, int[][] rel) {
            for (int[] indice1 : rel) {
                for (int[] indice2 : rel) {
                    if (indice1[1]==indice2[0]) {
                        boolean existeixe=false;
                        for (int[] par3 : rel) {
                            if (indice1[0]==par3[0] 
                                    &&indice2[1]==par3[1]) {
                                existeixe=true;
                            }
                        }
                         return true;
                    }
                }
            }
               return false;
        }



    /*
     * Comprovau si les relacions `rel1` i `rel2` són els grafs de funcions amb domini i codomini
     * `a`. Si ho son, retornau el graf de la composició `rel2 ∘ rel1`. Sino, retornau null.
     *
     * Podeu soposar que `a`, `rel1` i `rel2` estan ordenats de menor a major (les relacions,
     * lexicogràficament).
     */
    static int[][] exercici4(int[] a, int[][] rel1, int[][] rel2) {
             HashSet<int[]> elements=new HashSet<>();
            int[][] composicion;
             boolean bool=false;
              if(!funcio(a,rel1)||!funcio(a,rel2)) {bool=true;}
            if (bool) {
                return null;
            }
             for (int[] indice : rel1) {
                   for (int[] indice2 : rel2) {
                    if (indice[1]==indice2[0]) {
                        elements.add(new int[]{indice[0], indice2[1]});
                    }
                }
            }
            int indice=0;
            composicion=new int[elements.size()][2];
            for (int[] num : elements) {
                  if (!in(composicion,num)) {
                    composicion[indice++] = num;
                 }
            }
            return composicion;
        }
        static boolean in(int[][] arr, int[] comp) {
            for (int[] indice1 : arr) {
                if (comp[0]==indice1[0]&&comp[1]==indice1[1]) {
                    return true;
                }
            }
            return false;
        }

        // Método que verifica si una relación es una función gráfica
        public static boolean funcio(int[] a, int[][] rel) {
            boolean[] domini = new boolean[a.length];
             boolean[] codomini = new boolean[a.length];
            for (int[] indice : rel) {
                  int dominio=indice[0];
                  int codominio=indice[1];
                 for (int indice2=0;indice2<a.length;indice2++) {
                      if (a[indice2]==dominio) {
                         if (domini[indice2]) {
                             return false;
                        }
                         domini[indice2]=true;
                    }
                     if (a[indice2]==codominio) {
                         if (codomini[indice2]) {
                               return false;
                        }
                           codomini[indice2]=true;
                    }
                }
            }
            for (int indice=0;indice<a.length;indice++) {
                if (!domini[indice]||!codomini[indice]) {
                    return false;
                }
            }
            return true;
        }

    /*
     * Comprovau si la funció `f` amb domini `dom` i codomini `codom` té inversa. Si la té, retornau
     * el seu graf (el de l'inversa). Sino, retornau null.
     */
     static int[][] exercici5(int[] dom, int[] codom, Function<Integer, Integer> f) {
            int[][] inversa=new int[dom.length][2];
            for (int indice1 : dom) {
                    for (int indice2 : dom) {
                       if (f.apply(indice1)==f.apply(indice2) && indice1!=indice2) {
                           return null;
                    }
                }
            }
             for (int indice1 : codom) {
                  boolean booly = false;
                for (int indice2 : dom) {
                    if (f.apply(indice2) == indice1) {
                        booly = true;
                    }
                }
                    if (!booly) {
                      return null;
                }
            }
             for (int indice=0;indice<dom.length;indice++) {
                   inversa[indice]=new int[]{f.apply(dom[indice]),dom[indice]};
            }
             return inversa; // TODO
        }

    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1
      // |(a u b) × (a \ c)|

      assertThat(
          exercici1(
            new int[] { 0, 1, 2 },
            new int[] { 1, 2, 3 },
            new int[] { 0, 3 }
          )
          == 8
      );

      assertThat(
          exercici1(
            new int[] { 0, 1 },
            new int[] { 0 },
            new int[] { 0 }
          )
          == 2
      );

      // Exercici 2
      // nombre d'elements de la clausura d'equivalència

      final int[] int08 = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };

      assertThat(exercici2(int08, generateRel(int08, (x, y) -> y == x + 1)) == 81);

      final int[] int123 = { 1, 2, 3 };

      assertThat(exercici2(int123, new int[][] { {1, 3} }) == 5);

      // Exercici 3
      // Si rel és un ordre total, retornar les arestes del diagrama de Hasse

      final int[] int05 = { 0, 1, 2, 3, 4, 5 };

      assertThat(exercici3(int05, generateRel(int05, (x, y) -> x >= y)) == 5);
      assertThat(exercici3(int08, generateRel(int05, (x, y) -> x <= y)) == -2);

      // Exercici 4
      // Composició de grafs de funcions (null si no ho son)

      assertThat(
          exercici4(
            int05,
            generateRel(int05, (x, y) -> x*x == y),
            generateRel(int05, (x, y) -> x == y)
          )
          == null
      );


      var ex4test2 = exercici4(
          int05,
          generateRel(int05, (x, y) -> x + y == 5),
          generateRel(int05, (x, y) -> y == (x + 1) % 6)
        );

      assertThat(
          Arrays.deepEquals(
            lexSorted(ex4test2),
            generateRel(int05, (x, y) -> y == (5 - x + 1) % 6)
          )
      );

      // Exercici 5
      // trobar l'inversa (null si no existeix)

      assertThat(exercici5(int05, int08, x -> x + 3) == null);

      assertThat(
          Arrays.deepEquals(
            lexSorted(exercici5(int08, int08, x -> 8 - x)),
            generateRel(int08, (x, y) -> y == 8 - x)
          )
      );
    }

    /**
     * Ordena lexicogràficament un array de 2 dimensions
     * Per exemple:
     *  arr = {{1,0}, {2,2}, {0,1}}
     *  resultat = {{0,1}, {1,0}, {2,2}}
     */
    static int[][] lexSorted(int[][] arr) {
      if (arr == null)
        return null;

      var arr2 = Arrays.copyOf(arr, arr.length);
      Arrays.sort(arr2, Arrays::compare);
      return arr2;
    }

    /**
     * Genera un array int[][] amb els elements {a, b} (a de as, b de bs) que satisfàn pred.test(a, b)
     * Per exemple:
     *   as = {0, 1}
     *   bs = {0, 1, 2}
     *   pred = (a, b) -> a == b
     *   resultat = {{0,0}, {1,1}}
     */
    static int[][] generateRel(int[] as, int[] bs, BiPredicate<Integer, Integer> pred) {
      var rel = new ArrayList<int[]>();

      for (int a : as) {
        for (int b : bs) {
          if (pred.test(a, b)) {
            rel.add(new int[] { a, b });
          }
        }
      }

      return rel.toArray(new int[][] {});
    }

    /// Especialització de generateRel per a = b
    static int[][] generateRel(int[] as, BiPredicate<Integer, Integer> pred) {
      return generateRel(as, as, pred);
    }
  }

  /*
   * Aquí teniu els exercicis del Tema 3 (Grafs).
   *
   * Els (di)grafs vendran donats com llistes d'adjacència (és a dir, tractau-los com diccionaris
   * d'adjacència on l'índex és la clau i els vèrtexos estan numerats de 0 a n-1). Per exemple,
   * podem donar el graf cicle d'ordre 3 com:
   *
   * int[][] c3dict = {
   *   {1, 2}, // veïns de 0
   *   {0, 2}, // veïns de 1
   *   {0, 1}  // veïns de 2
   * };
   *
   * **NOTA: Els exercicis d'aquest tema conten doble**
   */
  static class Tema3 {
    /*
     * Determinau si el graf és connex. Podeu suposar que `g` no és dirigit.
     */
     static boolean exercici1(int[][] g) {
            //Inicialització d'un contador a 0
            int cont=0;
            //declaració de un boolea
            boolean punts=false;
            //Bucle per buidar g
            for(int indice=0;indice<g.length&&!punts;indice++){
                try{
                    if(g.length>0){
                        cont=indice;
                        punts=true;
                    }
                }
                catch(Exception e){      
                }             
            }
            //Tornam un valor o un altre depenent del resultat del boolea
            if(!punts){
                return punts;
            }
            //bucle per buidar g
            for (int[] x : g) {
                int contador = 0;
                //Condicional comprovat amb el submetode ExisteixRecorregut
                if (!ExisteixRecorregut(g,x,contador,cont)) {
                    return false; 
                }
            }
            return true;
        }
        static boolean ExisteixRecorregut(int[][] g, int[] x, int contador, final int cont) {
            //Condicional que comprova el valor del contador amb el tamany de l'array
            if (contador>g.length) {
                //tornam false
                return false;
            }
            //Declaracio del boolea a false
            boolean encontrado;
             encontrado=false;
            //Condicional
                if (x.length==0) {
                return false;
            }
            //Buidam l array
              for (int elemento : x) {
                if (elemento==cont) {
                    return true;
                }
                encontrado=ExisteixRecorregut(g,g[elemento],contador+1,cont);
                if (encontrado) {
                    return true;
                }
            }
            return false;
        }


    /*
     * Donat un tauler d'escacs d'amplada `w` i alçada `h`, determinau quin és el mínim nombre de
     * moviments necessaris per moure un cavall de la casella `i` a la casella `j`.
     *
     * Les caselles estan numerades de `0` a `w*h-1` per files. Per exemple, si w=5 i h=2, el tauler
     * és:
     *   0 1 2 3 4
     *   5 6 7 8 9
     *
     * Retornau el nombre mínim de moviments, o -1 si no és possible arribar-hi.
     */
    static int exercici2(int w, int h, int i, int j) {
            int[] vec1=null;
            int[] vec2=null;
            int[][] tablero=new int[w][h];
            int num=0;
            for (int indice=0;indice<w;indice++) {
                for (int indice1=0;indice1<h;indice1++) {
                  if (num==i) {
                    vec1 = new int[]{indice, indice1};
                    }
                  if (num==j) {
                    vec2=new int[]{indice, indice1}; 
                    }
               tablero[indice][indice1]=num++;
                }
            }
            //Ara hem trobat les pisicions i y j
            //Verificacio si existeixen les posicions en les cuals estan
            if (vec1==null||vec2==null) {
              return -1;
            }
            int solucio=RecorregutCavall(tablero, 0, vec1, vec2, tablero.length + 1);
            return solucio;
        }

        static int RecorregutCavall(int[][] tablero, int p, int[] vec1, int[] vec2, int distancia) {
            //Declaracio d'una variable
            boolean bool1=false;
            if (vec1[0]>=tablero.length||vec1[1]>=tablero[0].length||vec1[0]< 0||vec1[1]<0) {bool1=true;}
            
            if (bool1) {
                return -1;
            }//Hem comprovat si es troba en el tablero

            boolean bool2=false;
            if (dist(vec1,vec2)>distancia) {bool2=true;}
            
            if (bool2) {
                return -1;
            }//Comprovam si la distancia es major que la distancia permesa

           boolean bool3=false;
            if (tablero[vec1[0]][vec1[1]]==tablero[vec2[0]][vec2[1]]) {bool3=true;}
            
            if (bool3) {
                return p;
            }//Comprovam si la posicio actual es la posicio a la qual volem arribar

            boolean bool4=false;
            if (p>tablero.length+5) {bool4=true;}
            if (bool4) {
                return -1;
            }//Comprovam si esteim dins el rang permes
             //Moviments del caball
              int[] vectorX = {1, -1, 1, -1, 2, -2, -2, 2};
              int[] vectorY = {2, 2, -2, -2, -1, 1, -1, 1};
           int pp;
             pp=p;
                 p=-1;
            for (int indice=0;indice<vectorX.length;indice++) {
                  int pases=RecorregutCavall(tablero,pp+1,new int[]{vec1[0]+vectorX[indice],vec1[1]+vectorY[indice]},vec2,distancia);
                     if (pases==-1) {
                  continue;
                }
                     if (pases<p||p==-1||p==0) {
                  p=pases;
                }
            } //Moviments del caball
            return p;
        }
        static int dist(int[] vec1, int[] vec2) {
            int x=vec1[0]-vec2[0];
                 if (x<0) {
                x=-x;
             }
            int y=vec1[1]-vec2[1];
                if (y<0) {
                y=-y;
            }
                if (y<x) {
                return x;
            }
            return y;
        }


    /*
     * Donat un arbre arrelat (graf dirigit `g`, amb arrel `r`), decidiu si el vèrtex `u` apareix
     * abans (o igual) que el vèrtex `v` al recorregut en preordre de l'arbre.
     */
    static boolean exercici3(int[][] g, int r, int u, int v) {
            //Declaració d'una variable booleanan per retornar el valor
            boolean bool=false;
            //Declaració de una variable per almacenar la solucio
            int solucio;
            //declaracio d'un array boolea
            boolean [] arraybool= new boolean[1];
                  solucio=preordre(g, r, u, v, arraybool);
            //Condicional per trobar el valor a retornar depenguent del resultat
            if(solucio >= 0) {
                bool=true;
            }
            else {
                bool=false;
            }
            //retornam el resultat
            return bool;
        }
//Submetode que recorr en preordre
        static int preordre(int[][] g, int r, int u, int v, boolean[] apareix) {
               if (r==u) {
                apareix[0]=true;
            }
                if (r==v) {
                if (apareix[0]) {
                    return 0;
                 } else {
                    return -1;
                   }
               }
                 for (int x : g[r]) {
                 int resultado = preordre(g, x, u, v, apareix);
                   if (resultado!=2) {
                     return resultado;
                }
            }
            return 2;
        }


    /*
     * Donat un recorregut en preordre (per exemple, el primer vèrtex que hi apareix és `preord[0]`)
     * i el grau de cada vèrtex (per exemple, el vèrtex `i` té grau `d[i]`), trobau l'altura de
     * l'arbre corresponent.
     *
     * L'altura d'un arbre arrelat és la major distància de l'arrel a les fulles.
     */
      static int exercici4(int[] preord, int[] d) {
            //Declaració d'un array per almacenar els elements
            int[] elements = new int[d.length];
            //Declaració de una variable per guardar l altura maxima trobada
            int altura=0; 
            //Declaració d un indice inicialitzat a -1
            int indice=-1;
// Bucle per buidar l array
            for (int x : d) {
                if (indice+1>altura) {
                    altura=indice+1;
                      }
                elements[++indice]=x;
                elements[indice]-=1;
                //Bucle
                while (elements[indice] <= 0) {
                    //Inice=-1
                    indice -= 1;
                    if (indice<0){
                        return altura;
                    }
                          }
                }
            //Retornam el valor
            return altura;
        }


    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1
      // G connex?

      final int[][] B2 = { {}, {} };

      final int[][] C3 = { {1, 2}, {0, 2}, {0, 1} };

      final int[][] C3D = { {1}, {2}, {0} };

      assertThat(exercici1(C3));
      assertThat(!exercici1(B2));

      // Exercici 2
      // Moviments de cavall

      // Tauler 4x3. Moviments de 0 a 11: 3.
      // 0  1   2   3
      // 4  5   6   7
      // 8  9  10  11
      assertThat(exercici2(4, 3, 0, 11) == 3);

      // Tauler 3x2. Moviments de 0 a 2: (impossible).
      // 0 1 2
      // 3 4 5
      assertThat(exercici2(3, 2, 0, 2) == -1);

      // Exercici 3
      // u apareix abans que v al recorregut en preordre (o u=v)

      final int[][] T1 = {
        {1, 2, 3, 4},
        {5},
        {6, 7, 8},
        {},
        {9},
        {},
        {},
        {},
        {},
        {10, 11},
        {},
        {}
      };

      assertThat(exercici3(T1, 0, 5, 3));
      assertThat(!exercici3(T1, 0, 6, 2));

      // Exercici 4
      // Altura de l'arbre donat el recorregut en preordre

      final int[] P1 = { 0, 1, 5, 2, 6, 7, 8, 3, 4, 9, 10, 11 };
      final int[] D1 = { 4, 1, 3, 0, 1, 0, 0, 0, 0, 2,  0,  0 };

      final int[] P2 = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
      final int[] D2 = { 2, 0, 2, 0, 2, 0, 2, 0, 0 };

      assertThat(exercici4(P1, D1) == 3);
      assertThat(exercici4(P2, D2) == 4);
    }
  }

  /*
   * Aquí teniu els exercicis del Tema 4 (Aritmètica).
   *
   * En aquest tema no podeu:
   *  - Utilitzar la força bruta per resoldre equacions: és a dir, provar tots els nombres de 0 a n
   *    fins trobar el que funcioni.
   *  - Utilitzar long, float ni double.
   *
   * Si implementau algun dels exercicis així, tendreu un 0 d'aquell exercici.
   */
  static class Tema4 {
    /*
     * Calculau el mínim comú múltiple de `a` i `b`.
     */
   static int exercici1(int a, int b) {
    //Valor absolut!!!!
           if(a<0) {  a=-a; }  
            if(b<0) {  b=-b;  }
         //Declaración de las variables denominador, numerador
        int denominador, numerador;
        //Comprobamos cual de los dos números és más grande a o b para asignar los valores del denominador i númerados
        if (a>b) {
            numerador=a;
            denominador=b;
        }
        else{
            numerador=b;
            denominador=a;
        }
        //Declaramos la variable resto inicializada a 1;
        int resto=1;
        //declaración de un bucle que sigue mientras el resto sea diferente a 0
        while(resto!=0) {
            //Calculamos el resto
            resto=numerador%denominador;
            //Condicional que comprueba si el resto es 0 para salir del bucle
            if (resto==0) {
                break;
            }
            //Asignación del nuevo numerador y divisor
            numerador=denominador;
            denominador=resto;
        }
        
        //calculamos el minimo común multiple
        int mcm=(a*b)/denominador;
        
        //devolvemos el valor
      return mcm; // TODO
    }

    /*
     * Trobau totes les solucions de l'equació
     *
     *   a·x ≡ b (mod n)
     *
     * donant els seus representants entre 0 i n-1.
     *
     * Podeu suposar que `n > 1`. Recordau que no no podeu utilitzar la força bruta.
     */
    static int[] exercici2(int a, int b, int n) {
                //Declaración de las variables denominador, numerador
        int denominador, numerador;
        //Comprobamos cual de los dos números és más grande a o b para asignar los valores del denominador i númerados
        if (a>n) {
            numerador=a;
            denominador=n;
        }
        else{
            numerador=n;
            denominador=a;
        }
        //Declaramos la variable resto inicializada a 1;
        int resto=1;
        //declaración de un bucle que sigue mientras el resto sea diferente a 0
        while(resto!=0) {
            //Calculamos el resto
            resto=numerador%denominador;
            //Condicional que comprueba si el resto es 0 para salir del bucle
            if (resto==0) {
                break;
            }
            //Asignación del nuevo numerador y divisor
            numerador=denominador;
            denominador=resto;
        }
        int mcd=denominador;

        //Declaram un array inicializat a 0
        int [] array=new int[0];
        //Miram si te solucio
         if (b%mcd!=0) {
             return array;
            }
        //realitzam la resta dels nombres ja que son masa grans segons quins utilitzant els residus
        n/=mcd;
        a/=mcd;
        b/=mcd;
        int x;
        int[] arrayint=Euclides(a, n);
            int t = arrayint[0];
         x=((t%n+n)%n)*b%n;
         List<Integer> Solucion=new ArrayList<>();
         for (int indice=0;indice<mcd;indice++) {
             Solucion.add((x+ indice*n)%(n*mcd));
         } //Ara hem comprovat totes les solucions
          //Derreres pases per tornar el rezultat
         int[]resultat=new int[Solucion.size()];
         for (int indice=0;indice<Solucion.size();indice++) {
             resultat[indice]=Solucion.get(indice);
            }
         return resultat;
        }
        static int[] Euclides(int a, int n) {
            if (n==0) {
                return new int[]{1, 0};
            }
            int[] result = Euclides(n, a % n);
            int int1 = result[1];
            int int2 = result[0] - (a / n) * result[1];
            return new int[]{int1, int2};
        }


    /*
     * Donats `a != 0`, `b != 0`, `c`, `d`, `m > 1`, `n > 1`, determinau si el sistema
     *
     *   a·x ≡ c (mod m)
     *   b·x ≡ d (mod n)
     *
     * té solució.
     */
    static boolean exercici3(int a, int b, int c, int d, int m, int n) {
            //Declaración del mcd de a i m
            int mcd1;
            mcd1=mcd(a,m);
             //Declaración del mcd de a i m
            int mcd2;
            mcd2=mcd(b,n);
            //residuo entre c i el mcd de a i m
            int residuo1;
            residuo1=c%mcd1;
            //residuo entre d i el mcd de b i n
            int residuo2;
            residuo2=d%mcd2;
            int dif;
            dif=(d/mcd2-c/mcd1)%mcd(m/mcd1,n/mcd2);
            //Declaracion de una variable booleana
            boolean bool;
            if (dif==0 && residuo1==0 && residuo2==0) {
                bool=true;
            }
            else {
                bool=false;
            }
            return bool;
        }
    
          static int mcd(int a, int b) {
              //Declaración de las variables denominador, numerador
        int denominador, numerador;
        //Comprobamos cual de los dos números és más grande a o b para asignar los valores del denominador i númerados
        if (a>b) {
            numerador=a;
            denominador=b;
        }
        else{
            numerador=b;
            denominador=a;
        }
        //Declaramos la variable resto inicializada a 1;
        int resto=1;
        //declaración de un bucle que sigue mientras el resto sea diferente a 0
        while(resto!=0) {
            //Calculamos el resto
            resto=numerador%denominador;
            //Condicional que comprueba si el resto es 0 para salir del bucle
            if (resto==0) {
                break;
            }
            //Asignación del nuevo numerador y divisor
            numerador=denominador;
            denominador=resto;
        }
        return denominador;
        }

    /*
     * Donats `n` un enter, `k > 0` enter, i `p` un nombre primer, retornau el residu de dividir n^k
     * entre p.
     *
     * Alerta perquè és possible que n^k sigui massa gran com per calcular-lo directament.
     * De fet, assegurau-vos de no utilitzar cap valor superior a max{n, p²}.
     *
     * Anau alerta també abusant de la força bruta, la vostra implementació hauria d'executar-se en
     * qüestió de segons independentment de l'entrada.
     */
    static int exercici4(int n, int k, int p) {
            //Calculam fi 
            int fi=p-1; 
            //Reduim k
            k=k%fi; 
            //Comprovam que n esta dins el rang
            n=((n%p)+p)%p;
            //declaracio d una variable inicialitzada a 0 amb la cual es tornarà el resultat
            int residuSol=1;
            //Calculam
            while (k>0) {
                if (k%2==1) {
             residuSol=(residuSol*n)%p; 
                }
                n=(n*n)%p;
                k = k / 2;
            }
       //Retornam el valor
            return ((residuSol%p)+p)%p;
        }

    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1
      // mcm(a, b)

      assertThat(exercici1(35, 77) == 5*7*11);
      assertThat(exercici1(-8, 12) == 24);

      // Exercici 2
      // Solucions de a·x ≡ b (mod n)

      assertThat(Arrays.equals(exercici2(2, 2, 4), new int[] { 1, 3 }));
      assertThat(Arrays.equals(exercici2(3, 2, 4), new int[] { 2 }));

      // Exercici 3
      // El sistema a·x ≡ c (mod m), b·x ≡ d (mod n) té solució?

      assertThat(exercici3(3, 2, 2, 2, 5, 4));
      assertThat(!exercici3(3, 2, 2, 2, 10, 4));

      // Exercici 4
      // n^k mod p

      assertThat(exercici4(2018, 2018, 5) == 4);
      assertThat(exercici4(-2147483646, 2147483645, 46337) == 7435);
    }
  }

  /**
   * Aquest mètode `main` conté alguns exemples de paràmetres i dels resultats que haurien de donar
   * els exercicis. Podeu utilitzar-los de guia i també en podeu afegir d'altres (no els tendrem en
   * compte, però és molt recomanable).
   *
   * Podeu aprofitar el mètode `assertThat` per comprovar fàcilment que un valor sigui `true`.
   */
  public static void main(String[] args) {
    Tema1.tests();
    Tema2.tests();
    Tema3.tests();
    Tema4.tests();
  }

  /// Si b és cert, no fa res. Si b és fals, llança una excepció (AssertionError).
  static void assertThat(boolean b) {
    if (!b)
      throw new AssertionError();
  }
}

// vim: set textwidth=100 shiftwidth=2 expandtab :
