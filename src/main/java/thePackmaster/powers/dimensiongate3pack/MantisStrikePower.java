package thePackmaster.powers.dimensiongate3pack;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MantisStrikePower extends AbstractPackmasterPower implements NonStackablePower {
    public static final String POWER_ID = makeID("MantisStrikePower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private final AbstractCard owningCard;

    public MantisStrikePower(AbstractCreature owner, AbstractCard card) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, 1);

        owningCard = card;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (damageAmount <= Wiz.p().currentBlock) {
            this.flash();
            updateDescription();
            addToBot(new AttackDamageRandomEnemyAction(owningCard, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
        return damageAmount;
    }

    @Override
    public void atEndOfRound() {
        removeThis();
    }


    @Override
    public void updateDescription() {

            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];

    }
}
