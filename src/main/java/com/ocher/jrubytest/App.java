package com.ocher.jrubytest;

import java.time.Duration;
import java.time.LocalDateTime;

import org.jruby.embed.PathType;
import org.jruby.embed.ScriptingContainer;

public class App
{
    public static void main( String[] args )
    {
        ScriptingContainer container = new ScriptingContainer();
        container.runScriptlet("puts 'Hello World from Ruby invoked from Java'");

        // Warm up JVM
        for(int i=0; i < 10000; i++) {
            container.runScriptlet(PathType.RELATIVE, "ruby/test_me.rb");
        }

        var startTime = LocalDateTime.now();
        for(int i=0; i < 10000; i++) {
            container.runScriptlet(PathType.RELATIVE, "ruby/test_me.rb");
        }

        var duration = Duration.between(startTime, LocalDateTime.now());
        System.out.println(String.format("Elapsec time: %dms", duration.toMillis()));
    }
}
