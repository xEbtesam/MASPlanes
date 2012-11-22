/*
 * Software License Agreement (BSD License)
 *
 * Copyright 2012 Marc Pujol <mpujol@iiia.csic.es>.
 *
 * Redistribution and use of this software in source and binary forms, with or
 * without modification, are permitted provided that the following conditions
 * are met:
 *
 *   Redistributions of source code must retain the above
 *   copyright notice, this list of conditions and the
 *   following disclaimer.
 *
 *   Redistributions in binary form must reproduce the above
 *   copyright notice, this list of conditions and the
 *   following disclaimer in the documentation and/or other
 *   materials provided with the distribution.
 *
 *   Neither the name of IIIA-CSIC, Artificial Intelligence Research Institute
 *   nor the names of its contributors may be used to
 *   endorse or promote products derived from this
 *   software without specific prior written permission of
 *   IIIA-CSIC, Artificial Intelligence Research Institute
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package es.csic.iiia.planes.maxsum;

import es.csic.iiia.planes.Task;
import es.csic.iiia.planes.messaging.AbstractMessage;
import es.csic.iiia.planes.messaging.MessagingAgent;

/**
 *
 * @author Marc Pujol <mpujol@iiia.csic.es>
 */
public class MSVariable2FunctionMessage extends AbstractMessage {

    private final Task task;
    private final double value;

    MSVariable2FunctionMessage(Task t, double value) {
        task = t;
        this.value = value;
    }

    public Task getTask() {
        return task;
    }

    public double getValue() {
        return value;
    }

    @Override
    public MSPlane getSender() {
        return (MSPlane)super.getSender();
    }

    @Override
    public void setSender(MessagingAgent sender) {
        if (!(sender instanceof MSPlane)) {
            throw new IllegalArgumentException("MSVariable2Function messages can only be sent by MSPlanes.");
        }

        super.setSender(sender);
    }

    @Override
    public String toString() {
        return "V(" + getSender() + ") -> F(" + task + ") : " + value;
    }

}
