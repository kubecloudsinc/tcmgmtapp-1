/**
 Diagnostics are the mechanism by which semantic outcomes are conveyed.  
 The ConditionCode enumeration defines the namespace and meanings for
 informational, warning, and error conditions that must be communicated 
 externally.  The Diagnostic class is intended to fully describe an outcome,
 combining a ConditionCode and a list of detail objects that can be used to
 supply additional information.  
 <p>
 The ValidationException uses Diagnostics to convey the results of parameter 
 validation failures.
 */
package com.onecloud.autotools.semantic.diagnostics;