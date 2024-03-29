package com.bowen.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class RunAsync1Test {
	public static void main(String[] args) throws Exception {
		/*runAsync();
		supplyAsync();*/
		/*whenComplete();*/
		/*thenApply();*/
		/*handle();*/
		/*thenAccept();*/
		/*thenRun();*/
		/*thenCombine();*/
		/*thenAcceptBoth();*/
		/*applyToEither();*/
		/*acceptEither();*/
		/*runAfterEither();*/
		/*runAfterBoth();*/
		thenCompose();
	}
	
	//无返回值
	public static void runAsync() throws Exception {
	    CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
	        try {
	            TimeUnit.SECONDS.sleep(1);
	        } catch (InterruptedException e) {
	        }
	        System.out.println("run end ...");
	    });
	    
	    future.get();
	}
	//有返回值
	public static void supplyAsync() throws Exception {         
	    CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
	        try {
	            TimeUnit.SECONDS.sleep(1);
	        } catch (InterruptedException e) {
	        }
	        System.out.println("run end ...");
	        return System.currentTimeMillis();
	    });

	    long time = future.get();
	    System.out.println("time = "+time);
	}
	
	public static void whenComplete() throws Exception {
	    CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
	        try {
	            TimeUnit.SECONDS.sleep(1);
	        } catch (InterruptedException e) {
	        }
	        if(new Random().nextInt()%2>=0) {
	            int i = 12/0;
	        }
	        System.out.println("run end ...");
	    });
	    
	    future.whenComplete(new BiConsumer<Void, Throwable>() {
	        @Override
	        public void accept(Void t, Throwable action) {
	            System.out.println("执行完成！");
	        }
	        
	    });
	    future.exceptionally(new Function<Throwable, Void>() {
	        @Override
	        public Void apply(Throwable t) {
	            System.out.println("执行失败！"+t.getMessage());
	            return null;
	        }
	    });
	    
	    TimeUnit.SECONDS.sleep(2);
	}
	
	private static void thenApply() throws Exception {
	    CompletableFuture<Long> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
	        @Override
	        public Long get() {
	            long result = new Random().nextInt(100);
	            int k = 12/0;
	            System.out.println("result1="+result);
	            return result;
	        }
	    }).thenApply(new Function<Long, Long>() {
	        @Override
	        public Long apply(Long t) {
	            long result = t*5;
	            System.out.println("result2="+result);
	            return result;
	        }
	    }).exceptionally(new Function<Throwable, Long>() {
			@Override
			public Long apply(Throwable t) {
				System.out.println("执行任务失败！");
				return -1L;
			}
	    	
		});
	    
	    long result = future.get();
	    System.out.println(result);
	}
	
	public static void handle() throws Exception{
	    CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {

	        @Override
	        public Integer get() {
	            int i= 10/0;
	            return new Random().nextInt(10);
	        }
	    }).handle(new BiFunction<Integer, Throwable, Integer>() {
	        @Override
	        public Integer apply(Integer param, Throwable throwable) {
	            int result = -1;
	            if(throwable==null){
	                result = param * 2;
	            }else{
	                System.out.println("msg:"+throwable.getMessage());
	            }
	            return result;
	        }
	     });
	    System.out.println("result:"+future.get());
	}
	public static void thenAccept() throws Exception{
	    CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
	        @Override
	        public Integer get() {
	            return new Random().nextInt(10);
	        }
	    }).thenAccept(integer -> {
	        System.out.println(integer);
	    });
	    future.get();
	}
	public static void thenRun() throws Exception{
	    CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
	        @Override
	        public Integer get() {
	        	int k = 10/0;
	            return new Random().nextInt(10);
	        }
	    }).thenRun(() -> {
	        System.out.println("thenRun ...");
	    });
	    future.get();
	}
	
	private static void thenCombine() throws Exception {
	    CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new Supplier<String>() {
	        @Override
	        public String get() {
	            return "hello1";
	        }
	    });
	    CompletableFuture<String> future2 = CompletableFuture.supplyAsync(new Supplier<String>() {
	        @Override
	        public String get() {
	            return "hello2";
	        }
	    });
	    CompletableFuture<String> result = future1.thenCombine(future2, new BiFunction<String, String, String>() {
	        @Override
	        public String apply(String t, String u) {
	            return t+" "+u;
	        }
	    });
	    System.out.println(result.get());
	}
	private static void thenAcceptBoth() throws Exception {
	    CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
	        @Override
	        public Integer get() {
	            int t = new Random().nextInt(3);
	            try {
	                TimeUnit.SECONDS.sleep(t);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.println("f1="+t);
	            return t;
	        }
	    });
	        
	    CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
	        @Override
	        public Integer get() {
	            int t = new Random().nextInt(3);
	            try {
	                TimeUnit.SECONDS.sleep(t);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.println("f2="+t);
	            return t;
	        }
	    });
	    f1.thenAcceptBoth(f2, new BiConsumer<Integer, Integer>() {
	        @Override
	        public void accept(Integer t, Integer u) {
	            System.out.println("f1="+t+";f2="+u+";");
	        }
	    });
	    Thread.sleep(1000*5);
	}
	private static void applyToEither() throws Exception {
	    CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
	        @Override
	        public Integer get() {
	            int t = new Random().nextInt(5);
	            try {
	                TimeUnit.SECONDS.sleep(t);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.println("f1="+t);
	            return t;
	        }
	    });
	    CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
	        @Override
	        public Integer get() {
	            int t = new Random().nextInt(5);
	            try {
	                TimeUnit.SECONDS.sleep(t);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.println("f2="+t);
	            return t;
	        }
	    });
	    
	    CompletableFuture<Integer> result = f1.applyToEither(f2, new Function<Integer, Integer>() {
	        @Override
	        public Integer apply(Integer t) {
	            System.out.println(t);
	            return t * 2;
	        }
	    });

	    System.out.println(result.get());
	}
	
	private static void acceptEither() throws Exception {
	    CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
	        @Override
	        public Integer get() {
	            int t = new Random().nextInt(3);
	            try {
	                TimeUnit.SECONDS.sleep(t);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.println("f1="+t);
	            return t;
	        }
	    });
	        
	    CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
	        @Override
	        public Integer get() {
	            int t = new Random().nextInt(3);
	            try {
	                TimeUnit.SECONDS.sleep(t);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.println("f2="+t);
	            return t;
	        }
	    });
	    f1.acceptEither(f2, new Consumer<Integer>() {
	        @Override
	        public void accept(Integer t) {
	            System.out.println(t);
	        }
	    });
	    Thread.sleep(1000*5);
	}
	
	private static void runAfterEither() throws Exception {
	    CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
	        @Override
	        public Integer get() {
	            int t = new Random().nextInt(3);
	            try {
	                TimeUnit.SECONDS.sleep(t);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.println("f1="+t);
	            return t;
	        }
	    });
	        
	    CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
	        @Override
	        public Integer get() {
	            int t = new Random().nextInt(3);
	            try {
	                TimeUnit.SECONDS.sleep(t);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.println("f2="+t);
	            return t;
	        }
	    });
	    f1.runAfterEither(f2, new Runnable() {
	        
	        @Override
	        public void run() {
	            System.out.println("上面有一个已经完成了。");
	        }
	    });
	}
	private static void runAfterBoth() throws Exception {
	    CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
	        @Override
	        public Integer get() {
	            int t = new Random().nextInt(3);
	            try {
	                TimeUnit.SECONDS.sleep(t);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.println("f1="+t);
	            return t;
	        }
	    });
	        
	    CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
	        @Override
	        public Integer get() {
	            int t = new Random().nextInt(3);
	            try {
	                TimeUnit.SECONDS.sleep(t);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.println("f2="+t);
	            return t;
	        }
	    });
	    f1.runAfterBoth(f2, new Runnable() {
	        
	        @Override
	        public void run() {
	            System.out.println("上面两个任务都执行完成了。");
	        }
	    });
	}
	private static void thenCompose() throws Exception {
        CompletableFuture<Integer> f = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                System.out.println("t1="+t);
                return t;
            }
        }).thenCompose(new Function<Integer, CompletionStage<Integer>>() {
            @Override
            public CompletionStage<Integer> apply(Integer param) {
                return CompletableFuture.supplyAsync(new Supplier<Integer>() {
                    @Override
                    public Integer get() {
                        int t = param *2;
                        System.out.println("t2="+t);
                        return t;
                    }
                });
            }
            
        });
        System.out.println("thenCompose result : "+f.get());
    }

}
