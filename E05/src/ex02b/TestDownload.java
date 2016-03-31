package ex02b;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TestDownload {
  private static final String[] links = new String[]{
      "http://www.google.com",
      "http://www.bing.com",
      "http://www.yahoo.com",
      "http://www.microsoft.com",
      "http://www.oracle.com"
  };

  public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
    long startTime = System.currentTimeMillis();
    WebDownload downloader = new WebDownload();

    // download(downloader);
    asyncDownload(startTime, downloader);

    long endTime = System.currentTimeMillis();
    System.out.println(String.format("total time: %d ms", endTime - startTime));
  }

  private static void download(WebDownload downloader) throws IOException {
    for (int i = 0; i < links.length; i++) {
      String link = links[i];
      String result = downloader.downloadUrl(link);
      System.out.println(String.format("%s downloaded (%d characters)", link, result.length()));
    }
  }

  private static void asyncDownload(long startTime, WebDownload downloader) {
    CompletableFuture<Void> all = CompletableFuture.runAsync(() ->{});

    for (int i = 0; i < links.length; i++) {
      final String link = links[i];
      CompletableFuture<Void> future = downloader.asyncDownloadUrl(link).thenAccept(result -> {
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("%s downloaded (%d characters) after %d ms", link, result.length(), endTime - startTime));
      });

      all = CompletableFuture.allOf(all, future);
    }

    all.thenAccept(voids -> {
      long endTime = System.currentTimeMillis();
    }).join();
  }
}
