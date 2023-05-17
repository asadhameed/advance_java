package repo.sec01;

import courseutil.Util;
import reactor.core.publisher.Mono;

public class FileReaderMonoDemo {

    public static void main(String[] args){
        FileReaderService.read("file3.txt").subscribe(Util.onNext(), Util.onError(), Util.onCompleted());

        FileReaderService.write("file3.txt", "hello world").subscribe(Util.onNext(), Util.onError(), Util.onCompleted());
    }
}
