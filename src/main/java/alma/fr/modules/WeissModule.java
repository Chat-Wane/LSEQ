package alma.fr.modules;

import java.math.BigInteger;

import alma.fr.basecomponents.BaseSimple;
import alma.fr.basecomponents.Basebase;
import alma.fr.basecomponents.IBase;
import alma.fr.strategiescomponents.EndingBoundaryIdProvider;
import alma.fr.strategiescomponents.IIdProviderStrategy;
import alma.fr.strategiescomponents.boundary.BoundaryValue;
import alma.fr.strategiescomponents.boundary.ConstantBoundary;
import alma.fr.strategiescomponents.boundary.IBoundary;
import alma.fr.strategychoicecomponents.IStrategyChoice;
import alma.fr.strategychoicecomponents.SingleStrategyChoice;

import com.google.inject.Binder;
import com.google.inject.Module;

/**
 * Simple Weiss module with a boundary+ ; base 1024 ; boundary 10;
 */
public class WeissModule implements Module {

	public void configure(Binder binder) {
		Integer baseBase = new Integer(64);
		BigInteger boundary = new BigInteger("1000000");

		/* BASE */
		binder.bind(Integer.class).annotatedWith(Basebase.class)
				.toInstance(baseBase);
		binder.bind(IBase.class).to(BaseSimple.class);

		/* STRATEGY */
		binder.bind(IBoundary.class).to(ConstantBoundary.class);
		binder.bind(BigInteger.class).annotatedWith(BoundaryValue.class)
				.toInstance(boundary);

		/* STRATEGY CHOICE */
		binder.bind(IIdProviderStrategy.class).to(
				EndingBoundaryIdProvider.class);
		binder.bind(IStrategyChoice.class).to(SingleStrategyChoice.class);

	}

}
