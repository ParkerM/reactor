/*
 * Copyright (c) 2011-2014 Pivotal Software, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package reactor.rx.action.passive;

import reactor.fn.Consumer;
import reactor.rx.action.Action;

/**
 * @author Stephane Maldini
 */
public class CallbackAction<T> extends Action<T, T> {

	private final Consumer<? super T> consumer;
	private final Consumer<Void> completeConsumer;

	public CallbackAction(Consumer<? super T> consumer, Consumer<Void> completeConsumer) {
		this.consumer = consumer;
		this.completeConsumer = completeConsumer;
	}

	@Override
	protected void doNext(T ev) {
		if(consumer != null){
			consumer.accept(ev);
		}
		broadcastNext(ev);
	}

	@Override
	protected void doComplete() {
		if(completeConsumer != null){
			completeConsumer.accept(null);
		}
		broadcastComplete();
	}
}
