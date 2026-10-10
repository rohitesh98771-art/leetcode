class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int maxDiff = 0;
        int[] freq = new int[100001];

        for (int i = 0; i < nums1.length; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            freq[diff]++;
            maxDiff = Math.max(maxDiff, diff);
        }

        int k = k1 + k2;

        for (int d = maxDiff; d > 0 && k > 0; d--) {
            if (freq[d] == 0) {
                continue;
            }

            int move = Math.min(freq[d], k);
            freq[d] -= move;
            freq[d - 1] += move;
            k -= move;

            if (freq[d] > 0) {
                break;
            }
        }

        while (k > 0) {
            boolean changed = false;

            for (int d = 1; d <= maxDiff && k > 0; d++) {
                if (freq[d] > 0) {
                    freq[d]--;
                    freq[d - 1]++;
                    k--;
                    changed = true;
                }
            }

            if (!changed) {
                break;
            }
        }

        long ans = 0;

        for (long d = 1; d <= maxDiff; d++) {
            ans += d * d * freq[(int) d];
        }

        return ans;
    }
}