import * as React from 'react'
import {injectPage} from '../lib/app'

import gen_p from '../public/gen_p.png'
import Button from "../lib/Button";

const Page1 = () => <div>
    <h1>Page 1</h1>
    <p>This is page 1</p>
    <div>
        <pre style={{color: 'cornsilk', backgroundColor: 'black', padding: 10}}>
        public static void main(String[] args) &#123;
            System.out.println();
        &#125;
        </pre>
        <h2>Заработало</h2>
        <img src={gen_p} alt="sdf" style={{border: "5px double darkolivegreen"}}/>
        <Button href="../index.jsp" label="index.jsp"/>
    </div>
</div>

injectPage(<Page1/>)