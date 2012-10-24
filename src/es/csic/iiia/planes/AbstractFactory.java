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
package es.csic.iiia.planes;

import es.csic.iiia.planes.definition.DTask;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Skeletal implementation of a Factory to minimize the effort required to build
 * an actual factory.
 * 
 * @author Marc Pujol <mpujol@iiia.csic.es>
 */
public abstract class AbstractFactory implements Factory {
    
    private final Configuration config;
    
    /**
     * World being used in the current simulation.
     * 
     * This *must* be set by the concrete Factory class when implementing the
     * {@link Factory#buildWorld() } method.
     * 
     * @see Factory#buildWorld() 
     */
    protected World world;
    
    public AbstractFactory(Configuration config) {
        this.config = config;
    }

    @Override
    public Operator buildOperator(List<DTask> tasks) {
        Operator o = new Operator(tasks);
        o.setStrategy(config.operatorStrategy);
        initialize(o);
        return o;
    }

    @Override
    public Plane buildPlane(Location location) {
        Plane p = null;
        try {
            p = config.planesClass.getConstructor(Location.class).newInstance(location);
        } catch (Exception ex) {
            Logger.getLogger(AbstractFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        initialize(p);
        return p;
    }

    @Override
    public Station buildStation(Location l) {
        Station s = new Station(l);
        initialize(s);
        return s;
    }

    @Override
    public Task buildTask(Location location) {
        Task t = new Task(location);
        initialize(t);
        return t;
    }

    /**
     * Initializes an element.
     * 
     * @param element to initialize.
     */
    protected void initialize(Element element) {
        element.setWorld(world);
        element.initialize();
    }
    
}