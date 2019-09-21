import java.util.*;

class Main {

    private static Scanner sc = new Scanner(System.in);
    private static Set<String> listOflines = new HashSet<>();
    public static void main(String[] args) {
       // int n = sc.nextInt();
       // int m = combine(5, 5);



        int n=22;
         for(int k=n; k>=1; k--) {
             printResults("",n, k);
         }


        //printResults("",8, 4);
       // System.out.println(combine(n,n));

    }

    private static void printResults(String prefix, int sum, int size){
        int max = sum - (size-1);
        if(max==1){
            String currentString = prefix + lineInt(1, size);
            if(listOflines.isEmpty() || !listOflines.contains(currentString)){
                System.out.println( currentString);
                listOflines.add(currentString);
            }
        } else {
            for(int j=2; j<=max; j++){
                if(size * j < sum){
                    continue;
                }
                int currentRatio = sum/j;
/*
                int remainder = sum%j;
                if(remainder==0 && size>currentRatio){
                    currentRatio--;
                }
                while(currentRatio*(j-1) + size > sum){
                    currentRatio--;
                }
*/

                for(int k=1; k<=currentRatio; k++) {
                    if( (size-k)*(j-1) + k*j < sum){
                        //System.out.println("it continues");
                      continue;
                    }
                    if( k*(j-1) + size > sum){
                        break;
                    }
                    int tempoSum = sum - j * k;
                    int tempoSize = size - k;
                   // System.out.println("temposum: "+tempoSum+"   tempoSize: "+tempoSize+"  current ratio: "+k+"  current max or j: "+j+"  sum: "+sum+" size: "+size);
                        printResults(prefix + lineInt(j, k), sum - j * k, size - k);
                }
                }
            }
        }


  private static int combine(int n, int k){
          if (n == k) {
              return 1 + combine(n, k - 1);
          }
          if (k == 0 || n < 0){
              return 0;
          }
          if (n == 0 || k == 1){
              return 1;
          }
          return combine(n, k - 1) + combine(n - k, k);
      }


      private static String lineInt(int n, int m){
        StringBuilder out= new StringBuilder();
        if(m==0){
            return "";
        }
        for(int i=0; i<m; i++){
            out.append(n+" ");
        }
        return out.toString();
      }
}
/*

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int toDecompose = scanner.nextInt();

        provideDecomposition(toDecompose, toDecompose, "");
    }

    private static void provideDecomposition(int toDecompose, int max, String out) {
        if (toDecompose == 0) {
            System.out.println(out);
        } else if (toDecompose > 0) {
            for (int i = 1; i <= max; i++) {
                provideDecomposition(toDecompose - i, i, out + i + " ");
            }
        }
    }
}
 */