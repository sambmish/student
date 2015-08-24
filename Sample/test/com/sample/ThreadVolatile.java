package com.sample;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadVolatile {

	AtomicInteger count = new AtomicInteger(0);
	AtomicLong longCount = new AtomicLong(0);

	public void main() throws InterruptedException {

		Thread r1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 3000; i++) {
//					count.incrementAndGet();
					longCount.incrementAndGet();
				}
			}
		});

		Thread r2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 3000; i++) {
//					count.incrementAndGet();
					longCount.incrementAndGet();
				}
			}
		});

		r1.start();
		r2.start();

		r1.join();
		r2.join();

		System.out.println(count.intValue());
		System.out.println(longCount.longValue());
	}

	public static void main(String[] args) throws InterruptedException {
		new ThreadVolatile().main();
	}

}
