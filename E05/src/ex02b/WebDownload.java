package ex02b;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class WebDownload {
  public String downloadUrl(String link) throws IOException {
    URL url = new URL(link);
    StringBuffer stringBuffer = new StringBuffer();
    try (Reader reader = new InputStreamReader(url.openStream())) {
      int i;
      while ((i = reader.read()) >= 0) {
        stringBuffer.append((char) i);
      }
    }
    return stringBuffer.toString();
  }

  public CompletableFuture<String> asyncDownloadUrl(String link) {
    return CompletableFuture.supplyAsync(() -> {
      try {
        URL url = new URL(link);
        StringBuffer stringBuffer = new StringBuffer();
        try (Reader reader = new InputStreamReader(url.openStream())) {
          int i;
          while ((i = reader.read()) >= 0) {
            stringBuffer.append((char) i);
          }
        }
        return stringBuffer.toString();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });
  }
}
