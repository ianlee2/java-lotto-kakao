public class LottoResult {
    private Integer firstPrize;
    private Integer secondPrize;
    private Integer thirdPrize;
    private Integer fourthPrize;
    private Integer fifthPrize;

    public LottoResult() {
        this.firstPrize = 0;
        this.secondPrize = 0;
        this.thirdPrize = 0;
        this.fourthPrize = 0;
        this.fifthPrize = 0;
    }


    public void addResult(int matchCount, boolean bonusMatch) {
        LottoWinningInfo lottoWinningInfo = LottoWinningInfo.getLottoWinningInfo(matchCount, bonusMatch);
        if (lottoWinningInfo == LottoWinningInfo.MATCH_3) {
            fifthPrize += 1;
            return;
        }
        if (lottoWinningInfo == LottoWinningInfo.MATCH_4) {
            fourthPrize += 1;
            return;
        }
        if (lottoWinningInfo == LottoWinningInfo.MATCH_5) {
            thirdPrize += 1;
            return;
        }
        if (lottoWinningInfo == LottoWinningInfo.MATCH_5_BONUS) {
            secondPrize += 1;
            return;
        }
        if (lottoWinningInfo == LottoWinningInfo.MATCH_6) {
            firstPrize += 1;
        }
        // 1,2,3,4,5 등 다 테스트, 아무것도 해당안되는것도 테스트, matchCount = 3, bonusMatch = true 이면 5등 잘 나오는지 테스트
    }

    public Integer getFirstPrize() {
        return firstPrize;
    }

    public Integer getSecondPrize() {
        return secondPrize;
    }

    public Integer getThirdPrize() {
        return thirdPrize;
    }

    public Integer getFourthPrize() {
        return fourthPrize;
    }

    public Integer getFifthPrize() {
        return fifthPrize;
    }
}
