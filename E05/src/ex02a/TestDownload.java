package ex02a;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

    download(downloader);
    asyncDownload(downloader);

    long endTime = System.currentTimeMillis();
    System.out.println(String.format("total time: %d ms", endTime - startTime));
  }

  private static void download(WebDownload downloader) throws InterruptedException, ExecutionException, IOException {
    for (int i = 0; i < links.length; i++) {
      String link = links[i];
      String result = downloader.downloadUrl(link);
      System.out.println(String.format("%s downloaded (%d characters)", link, result.length()));
    }
  }

  private static void asyncDownload(WebDownload downloader) throws InterruptedException, ExecutionException {
    List<Future<String>> futures = new ArrayList<>();
    for (int i = 0; i < links.length; i++) {
      futures.add(downloader.asyncDownloadUrl(links[i]));
    }

    for (int i = 0; i < links.length; i++) {
      String result = futures.get(i).get();
      System.out.println(String.format("%s downloaded (%d characters)", links[i], result.length()));
    }
  }
}
