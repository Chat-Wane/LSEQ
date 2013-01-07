package alma.fr.modules;

import java.math.BigInteger;

import alma.fr.basecomponents.BaseDouble;
import alma.fr.basecomponents.Basebase;
import alma.fr.basecomponents.IBase;
import alma.fr.ponderator.IPonderator;
import alma.fr.ponderator.LinearPonderator;
import alma.fr.strategiescomponents.BeginningBoundaryIdProvider;
import alma.fr.strategiescomponents.EndingBoundaryIdProvider;
import alma.fr.strategiescomponents.IIdProviderStrategy;
import alma.fr.strategiescomponents.boundary.BoundaryValue;
import alma.fr.strategiescomponents.boundary.ConstantBoundary;
import alma.fr.strategiescomponents.boundary.IBoundary;
import alma.fr.strategychoicecomponents.IStrategyChoice;
import alma.fr.strategychoicecomponents.Strat1;
import alma.fr.strategychoicecomponents.Strat2;
import alma.fr.strategychoicecomponents.WindowStrategyChoice;

import com.google.inject.Binder;
import com.google.inject.Module;

public class EditingModule implements Module {

	public void configure(Binder binder) {
		BigInteger baseBase = new BigInteger("2").pow(4);
		BigInteger boundary = new BigInteger("10");

		/* BASE */
		binder.bind(BigInteger.class).annotatedWith(Basebase.class).toInstance(
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
		binder.bind(IStrategyChoice.class).to(WindowStrategyChoice.class);
		binder.bind(IPonderator.class).to(LinearPonderator.class);

	}

}
