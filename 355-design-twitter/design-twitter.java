class Twitter {

    private static int time = 0;

    class Tweet {
        int id, time;
        Tweet next;

        Tweet(int id) {
            this.id = id;
            this.time = Twitter.time++;
        }
    }

    Map<Integer, Set<Integer>> followMap = new HashMap<>();
    Map<Integer, Tweet> tweetMap = new HashMap<>();

    public Twitter() {}

    public void postTweet(int userId, int tweetId) {
        Tweet tweet = new Tweet(tweetId);
        tweet.next = tweetMap.get(userId);
        tweetMap.put(userId, tweet);
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();

        followMap.putIfAbsent(userId, new HashSet<>());
        followMap.get(userId).add(userId);

        PriorityQueue<Tweet> pq =
                new PriorityQueue<>((a, b) -> b.time - a.time);

        for (int followee : followMap.get(userId)) {
            if (tweetMap.containsKey(followee))
                pq.offer(tweetMap.get(followee));
        }

        while (!pq.isEmpty() && res.size() < 10) {
            Tweet tweet = pq.poll();
            res.add(tweet.id);
            if (tweet.next != null)
                pq.offer(tweet.next);
        }

        return res;
    }

    public void follow(int followerId, int followeeId) {
        followMap.putIfAbsent(followerId, new HashSet<>());
        followMap.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (followMap.containsKey(followerId) && followerId != followeeId)
            followMap.get(followerId).remove(followeeId);
    }
}