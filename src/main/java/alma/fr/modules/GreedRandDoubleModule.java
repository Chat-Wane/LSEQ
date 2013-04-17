package alma.fr.modules;

import java.math.BigInteger;

import alma.fr.basecomponents.BaseDouble;
import alma.fr.basecomponents.Basebase;
import alma.fr.basecomponents.IBase;
import alma.fr.strategiescomponents.BeginningBoundaryIdProvider;
import alma.fr.strategiescomponents.EndingBoundaryIdProvider;
import alma.fr.strategiescomponents.IIdProviderStrategy;
import alma.fr.strategiescomponents.boundary.BoundaryValue;
import alma.fr.strategiescomponents.boundary.ConstantBoundary;
import alma.fr.strategiescomponents.boundary.IBoundary;
import alma.fr.strategychoicecomponents.IStrategyChoice;
import alma.fr.strategychoicecomponents.RandomStrategyChoice;
import alma.fr.strategychoicecomponents.Strat1;
import alma.fr.strategychoicecomponents.Strat2;

import com.google.inject.Binder;
import com.google.inject.Module;

/**
 * Constant boundary but greed and double base
 */
public class GreedRandDoubleModule implements Module {

	public void configure(Binder binder) {
		Integer baseBase = new Integer(5);
		BigInteger boundary = new BigInteger("10");

		/* BASE */
		binder.bind(Integer.class).annotatedWith(Basebase.class).toInstance(
				baseBase);
		binder.bind(IBase.class).to(BaseDouble.class);
		
		/* STRATEGY */
		binder.bind(IBoundary.class).to(ConstantBoundary.class);
		binder.bind(BigInteger.class).annotatedWith(BoundaryValue.class)
				.toInstance(boundary);

		/* STRATEGY CHOICE */
		binder.bind(IIdProviderStrategy.class).annotatedWith(Strat1.class).to(
				BeginningBoundaryIdProvider.class);
		binder.bind(IIdProviderStrategy.class).annotatedWith(Strat2.class).to(
				EndingBoundaryIdProvider.class);
		binder.bind(IStrategyChoice.class).to(RandomStrategyChoice.class);
	}

}
