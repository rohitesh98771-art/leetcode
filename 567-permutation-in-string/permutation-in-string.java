class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] count = new int[26];

        for (char c : s1.toCharArray())
            count[c - 'a']++;

        int left = 0, right = 0;

        while (right < s2.length()) {
            count[s2.charAt(right) - 'a']--;

            while (count[s2.charAt(right) - 'a'] < 0) {
                count[s2.charAt(left) - 'a']++;
                left++;
            }

            if (right - left + 1 == s1.length())
                return true;

            right++;
        }

        return false;
    }
}