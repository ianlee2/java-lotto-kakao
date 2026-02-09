import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Collections;
import java.util.List;

public class AutoLottoGenerator implements LottoGenerator{

    // 로또 번호 1개 생성
    @Override
    public Lotto generateLotto() {
        List<Integer> pool = IntStream.rangeClosed(1, 45)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(pool);

        List<Integer> numbers = pool.stream()
                .limit(6)
                .sorted()
                .toList();

        return new Lotto(numbers);
    }
}