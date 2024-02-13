import uk.ac.soton.ecs.comp1206.labtestlibrary.interfaces.recursion.PalindromeChecker;

public class KPalindromeNotRecursive implements PalindromeChecker {

  public boolean isKPalindrome(String str, int num) {
    int n = str.length();

    if(n%2==0){
      n = n/2;
    }
    else {
      n = (n - 1) / 2;
    }
    String str1 = str.substring(0,n);
    String str2 = str.substring(n,n*2);
    String reversed2 = "";
    for (int i = str2.length()-1;i>=0;i--){
      reversed2 = reversed2 + str2.charAt(i);
    }
    System.out.println(reversed2);
    System.out.println(str1);
    return reversed2.toString().equals(str1);
    }

  }
