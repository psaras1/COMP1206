import uk.ac.soton.ecs.comp1206.labtestlibrary.interfaces.recursion.PalindromeChecker;

public class KPalindrome implements PalindromeChecker {


  public boolean isKPalindrome(String s, int k) {
    if (s.isEmpty() || s.length() == 1) {
      return true;
    }
    else if (s.charAt(0) == s.charAt(s.length() - 1)) {
      return isKPalindrome(s.substring(1, s.length() - 1), k);
    }
    else {

      return k>0&&( isKPalindrome(s.substring(1), k - 1) || isKPalindrome(
          s.substring(0, s.length() - 1), k - 1));

    }


  }
}
