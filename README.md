# Ace
An elegant & flexible general purpose utility library aiming compatibility & simplicity for the highest productivity.

## Characteristics
+ works with Java 1.5 or superior
+ is published under the MIT license
+ is released in a single jar file
+ has no external dependencies
+ has a memorable package structure
+ has no final classes
+ has no bytecode manipulation
+ is developed following the platform standards (when useful and possible): naming conventions, javadoc comments, junit tests, pmd rules, ant scripts, etc

## Motivation
> *"If you want more effective programmers, you will discover that they should not waste their time debugging, they should not introduce the bugs to starth with."*
- Edger Dijkstra, The Humble Programmer, 1972

## History

* 0.3.0 -- 05 Mar 2017
    + updated the project name to just Ace
* 0.2.8 -- 04 Mar 2017
    + released new binary
    + added ace.Sandboxed class
* 0.2.7 -- 02 Mar 2017
    + released new binary
    + added ace.LocalExceptionHandler class
    + improved ace.GlobalExceptionHandler class
* 0.2.6 -- 28 Feb 2017
    + released new binary
    + added ace.interfaces.ExceptionsMonitor interface
    + improved ace.interfaces.ExceptionsHandler interface
    + improved ace.GlobalExceptionHandler class
* 0.2.5 -- 25 Feb 2017
    + released new binary
    + improved classes to use the ace.Ace.GEH field
    + added ace.GlobalExceptionHandler class
    + added ace.interfaces.ExceptionsHandler interface
* 0.2.4 -- 10 Jan 2017
    + released new binary
    + improved ace.text.Strings
* 0.2.3 -- 26 Dec 2016
    + released new binary
    + moved ace.math.NUMBERS to ace.constants.NUMBERS
    + moved ace.text.STRINGS to ace.constants.STRINGS
    + moved ace.binary.BYTES to ace.constants.BYTES
    + moved ace.binary.SIZES to ace.constants.SIZES
    + moved ace.html.COLORS to ace.constants.HTMLCOLORS
    + moved ace.awt.COLORS to ace.constants.AWTCOLORS
    + added ace.constants package
* 0.2.2 -- 17 Dec 2016
    + released new binary
    + added ace.platform.Exceptions routines class
* 0.2.1 -- 09 Dec 2016
    + released new binary
    + added ace.randomness.Chance routines class
    + added ace.randomness.GUID routines class
    + added ace.randomness package
* 0.2.0 -- 08 Dec 2016
    + released new binary
    + improved ace.text.StringList
* 0.1.9 -- 02 Dec 2016
    + released new binary
    + improved ace.binary.BinaryComposer
* 0.1.8 -- 24 Nov 2016
    + released new binary
    + improved ace.app.SemanticVersion
* 0.1.7 -- 23 Nov 2016
    + released first public binary
* 0.1.6 -- 22 Nov 2016
    + improved ace.Ace
* 0.1.5 -- 29 Oct 2016
    + added ace.looping.BaseMapper class
    + added ace.looping.MapMapper class
    + added ace.looping.ListMapper class
    + added ace.looping.ArrayMapper class
    + added ace.looping.ItemsLooper class
    + added ace.interfaces.Alertable interface
    + added ace.interfaces.Decisor interface
    + added ace.interfaces.Initializable interface
    + added ace.interfaces.Processor interface
    + added ace.interfaces.Reseteable interface
    + added ace.interfaces.Startable interface
    + added ace.interfaces.Stoppable interface
    + added ace.interfaces.Taker interface
    + added ace.interfaces.Testable interface
    + added ace.interfaces.Treater interface
* 0.1.4 -- 22 Oct 2016
    + added ace.looping.Range class
    + added ace.looping package
    + added ace.platform.User class
    + added ace.platform.JRE class
    + added ace.platform.JVM class
* 0.1.3 -- 16 Oct 2016
    + improved unit tests
    + improved ace.platform.Classes
    + improved ace.math.Doubles
    + improved ace.math.Floats
    + improved ace.math.Integers
    + improved ace.math.Longs
    + improved ace.Ace
* 0.1.2 -- 15 Oct 2016
    + improved unit tests
    + improved ace.math.Doubles
    + improved ace.math.Floats
    + improved ace.math.Integers
    + improved ace.math.Longs
    + added ace.platform.Reflection routines class
    + added ace.text.StringList class
    + added ace.binary.BinaryComposer class
    + added ace.binary.BYTES constants class
    + added ace.binary.SIZES constants class
    + added ace.binary package
* 0.1.1 -- 10 Oct 2016
    + improved unit tests
    + improved ace.text.Strings
    + added ace.text.StringComposer
* 0.1.0 -- 09 Oct 2016
    + improved unit tests
    + improved ace.arrays.ByteArrays
    + improved ace.arrays.GenericArrays
    + moved ImmutableArray class to ace.arrays package
    + moved SemanticVersion class to ace.app package
    + moved ArgumentsManager class to ace.app package
* 0.0.9 -- 08 Oct 2016
    + added ace.arrays.GenericArrays routines class
    + added ace.arrays.ByteArrays routines class
    + added ace.arrays package
* 0.0.8 -- 03 Sep 2016
    + make first public release
    + added documentation comments
* 0.0.7 -- 28 Aug 2016
    + improved unit tests
    + added ace.html.Shorthand routines class
* 0.0.6 -- 21 Aug 2016
    + added ace.html.COLORS constants class
    + added ace.html package
    + added ace.awt.COLORS constants class
    + added ace.awt package
    + improved unit tests
    + improved ace.text.Strings
* 0.0.5 -- 20 Aug 2016
    + added unit tests
    + added ace.classes.ArgumentsManager class
* 0.0.4 -- 19 Aug 2016
    + added ace.platform.Classes routines class
    + added ace.platform.Packages routines class
    + added ace.platform package
* 0.0.3 -- 14 Aug 2016
    + added ace.text.STRINGS constants class
    + improved ace.text.Strings
* 0.0.2 -- 13 Aug 2016
    + added ace.interfaces.Immutable interface
    + added ace.interfaces package
    + added ace.classes.SemanticVersion class
    + added ace.classes.ImmutableArray class
    + added ace.classes package
    + added ace.text.Strings routines class
    + added ace.text package
* 0.0.1 -- 06 Aug 2016
    + added ace.math.Doubles routines class
    + added ace.math.Floats routines class
    + added ace.math.Longs routines class
    + added ace.math.Integers routines class
    + added ace.math.NUMBERS constants class
    + added ace.math package
    + added ace.Ace class
    + added ace package
    + configured to build with the JDK 1.5 platform
    + created the project

## License

Java Ace Toolkit
Copyright (c) 2016 Javier Santo Domingo (j-a-s-d@coderesearchlabs.com).

Permission is hereby granted, free of charge, to any
person obtaining a copy of this software and associated
documentation files (the "Software"), to deal in the
Software without restriction, including without limitation
the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the
Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice
shall be included in all copies or substantial portions of
the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY
KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
