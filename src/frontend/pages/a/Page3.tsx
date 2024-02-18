import * as React from 'react'
import {injectPage} from '../../lib/app'
import Button from "../../lib/Button";
import gen_p from '../../public/gen_p.png'


const Page3 = () => <div>
    <h1>Page 3</h1>
    <p>This is page 3</p>
    <Button href="../../index.jsp" label="index.jsp"/>
    <img src={gen_p} alt="sdf" style={{border: "5px double darkolivegreen", margin: 20}}/>
</div>

injectPage(<Page3/>)