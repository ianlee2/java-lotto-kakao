import java.util.List;

public class WinningLotto {
    private final Lotto winningLottoNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLottoNumbers, LottoNumber bonusNumber) {
        // 보너스 번호 체크
        if (winningLottoNumbers.contains(bonusNumber)) { throw new IllegalArgumentException("당첨 번호와 보너스 번호는 달라야 합니다. 보너스 번호를 다시 입력해주세요."); }

        // 검증 통과하면 넣어주기
        this.winningLottoNumbers = winningLottoNumbers;
        this.bonusNumber =  bonusNumber;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }


    public Lotto getWinningLottoNumbers() { return winningLottoNumbers; }
}
