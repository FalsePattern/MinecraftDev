/*
 * Minecraft Development for IntelliJ
 *
 * https://mcdev.io/
 *
 * Copyright (C) 2024 minecraft-dev
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, version 3.0 only.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.demonwav.mcdev.nbt.tags

import java.io.DataOutputStream

class TagLongArray(override val value: LongArray) : NbtValueTag<LongArray>(LongArray::class) {
    override val payloadSize = 4 + value.size * 8
    override val typeId = NbtTypeId.LONG_ARRAY

    override fun write(stream: DataOutputStream) {
        stream.writeInt(value.size)
        for (l in value) {
            stream.writeLong(l)
        }
    }

    override fun toString() = toString(StringBuilder(), 0, WriterState.COMPOUND).toString()

    override fun toString(sb: StringBuilder, indentLevel: Int, writerState: WriterState): StringBuilder {
        value.joinTo(buffer = sb, separator = ", ", prefix = "longs(", postfix = ")")
        return sb
    }

    override fun valueEquals(otherValue: LongArray) = this.value.contentEquals(otherValue)

    // This makes IntelliJ happy - and angry at the same time
    @Suppress("RedundantOverride")
    override fun equals(other: Any?) = super.equals(other)

    override fun hashCode() = this.value.contentHashCode()

    override fun valueCopy() = value.copyOf(value.size)
}
