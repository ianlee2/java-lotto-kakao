import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    private static final int LOTTO_SIZE = 6;

    protected final List<LottoNumber> lottoNumbers;

    public Lotto(List<Integer> nums) {
        validateSize(nums);

        this.lottoNumbers = nums.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        validateUnique(lottoNumbers);
    }

    private void validateSize(List<Integer> nums) {
        if (nums.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(
                    "로또 번호는 반드시 6개여야 합니다."
            );
        }
    }

    private void validateUnique(List<LottoNumber> numbers) {
        Set<LottoNumber> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(
                    "로또 번호는 중복될 수 없습니다."
            );
        }
    }

    public int size() {
        return lottoNumbers.size();
    }

    public List<LottoNumber> getLottoNumbers() {
        return List.copyOf(lottoNumbers);
    }

    @Override
    public String toString() {
        return lottoNumbers.stream()
                .map(LottoNumber::getValue)
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
    }
}