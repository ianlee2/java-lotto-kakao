import java.util.List;

public class WinningLotto extends Lotto {
    private final LottoNumber bonusNumber;

    public WinningLotto(List<Integer> nums, Integer bonusNumber) {
        super(nums);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
