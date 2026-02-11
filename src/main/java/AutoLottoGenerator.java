import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Collections;
import java.util.List;

public class AutoLottoGenerator implements LottoGenerator{

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    // 로또 번호 1개 생성
    @Override
    public Lotto generateLotto() {
        List<Integer> pool = IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(pool);

        List<Integer> numbers = pool.stream()
                .limit(LOTTO_NUMBER_COUNT)
                .sorted()
                .toList();

        return new Lotto(numbers);
    }
}