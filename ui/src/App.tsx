import {useEffect, useState} from 'react'
import './App.css'
import axios from 'axios';


type SensorData = {
    id: string,
    temperature: number;
    pressure: number
}

type SensorDataResponse = {
    data: SensorData[]
}

function App() {
    const [sensorData, setSensorData] = useState<SensorData[]>([]);

    useEffect(() => {
        axios.get<SensorDataResponse>('/api/sensor-data')
            .then(res => res.data.data)
            .then(setSensorData);
    }, [])

    return (
        <div className="sensor-data">
            {
                sensorData.map(d => (<div key={d.id}>
                    <div className="sensor">
                        <div>
                            <label>Temperature</label>
                            {d.temperature}
                        </div>
                        <div>
                            <label>Pressure</label>
                            {d.pressure}
                        </div>
                    </div>
                    <div className={'divider'}/>
                </div>))
            }
        </div>
    )
}

export default App
