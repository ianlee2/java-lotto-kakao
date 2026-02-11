import java.util.List;

public class WinningLotto extends Lotto {
    private LottoNumber bonusNumber;

    public WinningLotto(List<Integer> nums) {
        super(nums);
    }

    public void setBonusNumber(Integer number) {
        LottoNumber bonusNumber = new LottoNumber(number);
        if (this.lottoNumbers.contains(bonusNumber)) throw new IllegalArgumentException("당첨 번호와 보너스 번호는 달라야 합니다.");
        this.bonusNumber =  bonusNumber;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
