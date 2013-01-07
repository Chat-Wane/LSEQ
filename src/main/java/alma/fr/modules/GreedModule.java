package alma.fr.modules;

import java.math.BigInteger;

import alma.fr.basecomponents.BaseSimple;
import alma.fr.basecomponents.Basebase;
import alma.fr.basecomponents.IBase;
import alma.fr.strategiescomponents.BeginningBoundaryIdProvider;
import alma.fr.strategiescomponents.EndingBoundaryIdProvider;
import alma.fr.strategiescomponents.IIdProviderStrategy;
import alma.fr.strategiescomponents.boundary.BoundaryValue;
import alma.fr.strategiescomponents.boundary.ConstantBoundary;
import alma.fr.strategiescomponents.boundary.IBoundary;
import alma.fr.strategychoicecomponents.IStrategyChoice;
import alma.fr.strategychoicecomponents.RoundRobinStrategyChoice;
import alma.fr.strategychoicecomponents.Strat1;
import alma.fr.strategychoicecomponents.Strat2;

import com.google.inject.Binder;
import com.google.inject.Module;

/**
 * 
 * Same as Weiss Module but alternate boundary+ & boundary-
 * 
 */
public class GreedModule implements Module {

	public void configure(Binder binder) {
		BigInteger baseBase = new BigInteger("2").pow(64);
		BigInteger boundary = new BigInteger("1000000");

		/* BASE */
		binder.bind(BigInteger.class).annotatedWith(Basebase.class).toInstance(
				baseBase);
		binder.bind(IBase.class).to(BaseSimple.class);

		/* STRATEGY */
		binder.bind(IBoundary.class).to(ConstantBoundary.class);
		binder.bind(BigInteger.class).annotatedWith(BoundaryValue.class)
				.toInstance(boundary);

		/* STRATEGY CHOICE */
		binder.bind(IIdProviderStrategy.class).annotatedWith(Strat1.class).to(
				BeginningBoundaryIdProvider.class);
		binder.bind(IIdProviderStrategy.class).annotatedWith(Strat2.class).to(
				EndingBoundaryIdProvider.class);
		binder.bind(IStrategyChoice.class).to(RoundRobinStrategyChoice.class);
	}

}