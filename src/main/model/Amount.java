/*
 * Copyright (c) 2016, Leif Lindbäck
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of
 * conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided
 * with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.

 * Changes made to original code by Maida Mahamud
 */

package model;

/**
 * Represents an amount of money.
 */
public final class Amount {
    private final int amount;  // Stored as an integer value of cents for precision.

    /**
     * Creates an instance representing zero amount.
     */
    public Amount() {
        this(0);
    }

    /**
     * Creates an instance representing the specified amount.
     *
     * @param amount The monetary value represented in cents.
     */
    public Amount(int amount) {
        this.amount = amount;
    }

    /**
     * Subtracts another Amount from this Amount and returns the result.
     *
     * @param other The Amount to subtract from this instance.
     * @return A new Amount representing the result of the subtraction.
     */
    public Amount minus(Amount other) {
        return new Amount(this.amount - other.amount);
    }

    /**
     * Adds another Amount to this Amount and returns the result.
     *
     * @param other The Amount to add to this instance.
     * @return A new Amount representing the result of the addition.
     */
    public Amount plus(Amount other) {
        return new Amount(this.amount + other.amount);
    }

    /**
     * Multiplies this Amount by a specified factor and returns the result.
     *
     * @param factor The multiplier.
     * @return A new Amount representing the result of the multiplication.
     */
    public Amount multiply(double factor) {
        return new Amount((int) (this.amount * factor));
    }

    /**
     * Compares this Amount to another object to determine equality.
     * Two Amount instances are considered equal if they represent the same amount of money.
     *
     * @param other The object to compare this Amount against.
     * @return true if the other object is an instance of Amount and represents the same amount of money.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Amount that = (Amount) other;
        return this.amount == that.amount;
    }

    /**
     * Returns a string representation of the amount formatted in Swedish currency (SEK).
     * The format used is "XX:XX SEK", where XX is the integer amount in Swedish krona (SEK).
     *
     * @return A formatted string representing the monetary amount.
     */
    @Override
    public String toString() {
        int krona = amount / 100;
        int ore = amount % 100;
        return String.format("%d:%02d SEK", krona, ore);
    }
}