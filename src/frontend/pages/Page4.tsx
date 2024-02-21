import * as React from 'react'
import {injectPage} from '../lib/app'
import Button from "../lib/Button";

const Page4 = () => <div>
    <h1>Страница для Вилатия</h1>
    <p>This is page 4</p>
    <Button href="../index.jsp" label="index.jsp"/>
</div>

injectPage(<Page4/>)