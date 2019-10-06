import java.util.Scanner;

class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        char[] input = sc.next().toCharArray();
        int count = countOccurrencesOf(input,"ab");
        System.out.println(count);
    }

    private static int countOccurrencesOf(char[] input, String ab) {
        int l=input.length;
        int subL= ab.length();
        int count=0;
        if(l<subL || subL==0){
            return 0;
        }else{
            char[] subLChar = ab.toCharArray();
            for(int i=0; i<l; i++){
                if(matching(i, input, subLChar)){
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean matching(int i, char[] input, char[] subLChar) {
        int sub = subLChar.length;
        boolean cond = true;
        int l = input.length;
        if(i+sub > l){
            return false;
        }else{
            for(int j=i; j<i+sub; j++){
                cond = cond && input[j]==subLChar[j-i];
                if(!cond){
                    break;
                }
            }
        }
        return cond;
    }
}