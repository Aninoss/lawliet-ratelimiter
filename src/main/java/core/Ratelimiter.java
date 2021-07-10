package core;

public class Ratelimiter {

    private final int requestsPerSecond;
    private int frameRequests = 0;
    private int frameSecond = 0;

    public Ratelimiter(int requestsPerSecond) {
        this.requestsPerSecond = requestsPerSecond;
    }

    public synchronized int nextRequest() {
        int currentSecond = (int) (System.currentTimeMillis() / 1_000);
        if (currentSecond > frameSecond) {
            frameSecond = currentSecond;
            frameRequests = 1;
        } else if (++frameRequests > requestsPerSecond) {
            frameSecond++;
            frameRequests = 1;
        }

        return frameSecond;
    }

}
