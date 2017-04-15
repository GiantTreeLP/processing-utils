package org.gtlp.util.path

/**
 * Class to describe inconsistent data in [Path].
 * This exception should be thrown when the data does not meet the
 * expectations of an algorithm.
 */
class InconsistentDataException(s: String) : Throwable(s)