package com.github.mforoni.jbasic.demo;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.mforoni.jbasic.time.JDates;
import com.github.mforoni.jbasic.time.JLocalDates;
import com.github.mforoni.jbasic.time.JLocalDates.InferredLocalDate;

final class DateTimesDemo {

	private static final Logger LOGGER = LoggerFactory.getLogger(DateTimesDemo.class);

	private DateTimesDemo() {}

	private static void start() {
		LOGGER.info("Current timestamp is {}", JDates.currentTimestamp());
		LOGGER.info("Today date is {}", JLocalDates.today());
		LOGGER.info("Tomorrow date is {}", JLocalDates.tomorrow());
		LOGGER.info("Yesterday date is {}", JLocalDates.yesterday());
		String date = "02/03/09";
		List<InferredLocalDate> inferredDates = JLocalDates.inferredLocalDates(date);
		LOGGER.info("Inferred local date of {} are {}", date, inferredDates);
		date = "02/03/2009";
		inferredDates = JLocalDates.inferredLocalDates(date);
		LOGGER.info("Inferred local date of {} are {}", date, inferredDates);
	}

	public static void main(final String[] args) {
		start();
	}
}
